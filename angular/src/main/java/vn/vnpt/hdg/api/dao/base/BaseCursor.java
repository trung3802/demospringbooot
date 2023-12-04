package vn.vnpt.hdg.api.dao.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import vn.vnpt.hdg.api.utils.ConvertorUtils;

import org.json.JSONArray;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class BaseCursor {
    protected Connection connection;
    protected String storedProcName = "";
    protected String query = "";
    protected ArrayList<DBParameter> parameters = null;
    protected Object resultSet = null;

    protected JSONArray jsonResult;
    protected String json;

    protected boolean autoCommit = true;

    protected final static String CALL_STRING = "{call ";
    protected final static String CALL_OPEN_PAREN = "(";
    protected final static String CALL_PARM = "?";
    protected final static String CALL_COMMA = ",";
    protected final static String CALL_CLOSE = ")}";

    protected List<String> dataFields;

    public BaseCursor() {
    }

    @JsonProperty("raw")
    @JsonRawValue
    public String getJsonResultSet() throws
            IOException {
        String result = "{}";
        if (this.getResultSet() != null) {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(this.getResultSet());
        }
        return result;
    }

    @JsonProperty("raw")
    @JsonRawValue
    public String getJsonParameterValue(int index) {
        try {
            String result = "{}";
            DBParameter parm = this.getParameters().get(index);
            if (parm != null && parm.getValue() != null) {
                ObjectMapper mapper = new ObjectMapper();
                result = mapper.writeValueAsString(parm.getValue());
                try {
                    if (parm.getType() == -10) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeSQL() {
        Statement stm = null;
        try {
            stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(query);
            dataFields = getDataFields(resultSet);
            DBParameter dbParameter = new DBParameter();
            dbParameter.setValue(ConvertorUtils.convertResultSetIntoJSON(resultSet).toList());
            getParameters().add(dbParameter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(stm, connection);
        }
    }

    public void execute() throws SQLException {
        CallableStatement cs = null;
        try {
            String call = createCallString();
            cs = connection.prepareCall(call);
            int i = 1;
            boolean hasOutParameter = false;
            ArrayList<DBParameter> parameters = this.getParameters();
            for (DBParameter p : parameters) {
                if (p.isOutParameter()) {
                    cs.registerOutParameter(i, p.getType());
                    hasOutParameter = true;
                } else {
                    cs.setObject(i, p.getValue());
                }
                i++;
            }

            i = 1;
            cs.execute();
            if (hasOutParameter) {
//                cs.execute();
                for (DBParameter p : parameters) {
                    if (p.isOutParameter()) {
                        p.setValue(cs.getObject(i));
                    }
                    i++;
                }
            }

            ResultSet resultSet = cs.getResultSet();
            if (resultSet != null) {
                jsonResult = ConvertorUtils.convertResultSetIntoJSON(resultSet);
                json = jsonResult.toString();
            }
        } finally {
            closeQuietly(cs, connection);
        }
    }

    public void closeQuietly(Statement stm, Connection connection) {
        try {
            if (stm != null) {
                stm.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the storedProc
     */
    public String getStoredProcName() {
        return storedProcName;
    }

    /**
     *
     */
    public void setStoredProcName(final String storedProcName) {
        this.storedProcName = storedProcName;
    }

    /**
     * @return the autoCommit
     */
    public boolean isAutoCommit() {
        return autoCommit;
    }

    /**
     * @param autoCommit the autoCommit to set
     */
    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    /**
     * @return the resultSet
     */
    public Object getResultSet() {
        return resultSet;
    }

    /**
     * @return the parameters
     */
    public ArrayList<DBParameter> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<DBParameter>();
        }
        return parameters;
    }

    /**
     * @param index      - the first parameter is 0, the second is 1, and so on
     * @param value
     * @param isOutParam
     */
    public void addParameter(int index, Object value, boolean isOutParam) {
        DBParameter p = new DBParameter();
        p.setIndex(index);
        p.setValue(value);
        p.setOutParameter(isOutParam);
        p.setType(determineSqlType(value));

        this.getParameters().add(index, p);
    }

    /**
     * @param index - the first parameter is 0, the second is 1, and so on
     * @param type  - java.sql.Types int value of the parameter
     */
    public void addOutParameter(int index, int type) {
        DBParameter p = new DBParameter();
        p.setIndex(index);
        p.setType(type);
        p.setOutParameter(true);
        this.getParameters().add(index, p);
    }

    /**
     * @param index - the first parameter is 0, the second is 1, and so on
     * @param type  - java.sql.Types value of the parameter
     */
    public void addOutParameter(int index, Object type) {
        DBParameter p = new DBParameter();
        p.setIndex(index);
        p.setType((Integer) type);

        p.setOutParameter(true);
        this.getParameters().add(index, p);
    }

    public Object getParameter(int index) {
        return this.getParameters().get(index);
    }

    protected String createCallString() {

        StringBuffer sb = new StringBuffer();
        int parmCnt = this.getParameters().size();
        sb.append(CALL_STRING).append(storedProcName).append(CALL_OPEN_PAREN);

        for (int i = 0; i < parmCnt; i++) {
            sb.append(CALL_PARM);
            if (parmCnt > i + 1) {
                sb.append(CALL_COMMA);
            }
        }

        sb.append(CALL_CLOSE);
        return sb.toString();
    }

    protected List<String> getDataFields(ResultSet rs) {
        try {
            List<String> fields = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                fields.add(metaData.getColumnName(i + 1).toUpperCase());
            }

            return fields;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Determines SqlType of object. throws exception if unknown
     * <p>
     * NOTE: this is not meant to be a complete list, just picked most common
     * types to start with.
     *
     * @param obj
     * @return $returnType
     */
    public static int determineSqlType(Object obj) {
        int sqlType = Types.VARCHAR;
        if (obj == null) {
            // assuming a string if object is null
            sqlType = Types.VARCHAR;
        } else if (obj instanceof String) {
            sqlType = Types.VARCHAR;
        } else if (obj instanceof Integer) {
            sqlType = Types.INTEGER;
        } else if (obj instanceof Double) {
            sqlType = Types.DOUBLE;
        } else if (obj instanceof Float) {
            sqlType = Types.FLOAT;
        } else if (obj instanceof java.io.InputStream) {
            sqlType = Types.LONGVARBINARY;
        } else if (obj instanceof java.math.BigDecimal) {
            sqlType = Types.DECIMAL;
        } else if (obj instanceof java.math.BigInteger) {
            sqlType = Types.BIGINT;
        } else if (obj instanceof java.util.Date) {
            sqlType = Types.DATE;
        } else if (obj instanceof Date) {
            sqlType = Types.DATE;
        } else if (obj instanceof Timestamp) {
            sqlType = Types.TIMESTAMP;
        } else if (obj instanceof ResultSet) {
            // oracle.jdbc.OracleTypes.CURSOR
            sqlType = -10;
        } else if (obj instanceof Blob) {
            sqlType = Types.BLOB;
        } else if (obj instanceof Clob) {
            sqlType = Types.CLOB;
        } else if (obj instanceof Object[]) {
            sqlType = Types.ARRAY;
        } else {
            sqlType = Types.OTHER;
        }
        return sqlType;
    }
}
