package vn.vnpt.hdg.api.dao.base;

import lombok.Data;
import vn.vnpt.hdg.api.configs.AppConfigs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@Component
@Data
public class BaseCursorDAO extends BaseCursor {
    public void addOutParameter(DBParameter... outParam) {
        if (outParam != null) {
            for (DBParameter out : outParam) {
                this.getParameters().add(out);
            }
        }
    }

    public void addInParameter(Object... valueObjects) {
        for (Object value : valueObjects) {
            DBParameter p = new DBParameter();
            p.setValue(value);
            p.setOutParameter(false);
            p.setType(determineSqlType(value));
            this.getParameters().add(p);
        }
    }

    public void setDataSource(DataSource dataSource) {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Object getParamValueAt(int index) {
        try {
            return getParameters().get(index).getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<DBParameter> doProcedure(DataSource dataSource,
                                              String procedureName, DBParameter[] listOutType,
                                              Object... inputParam) throws SQLException, JSONException {
        setDataSource(dataSource);
        setStoredProcName(procedureName);
        addOutParameter(listOutType);
        addInParameter(inputParam);
        execute();
        return getParameters();
    }

    public ArrayList<DBParameter> doProcedure(String procedureName, DBParameter[] listOutType,
                                              Object... inputParam) throws SQLException, JSONException {
        setDataSource();
        setStoredProcName(procedureName);
        addOutParameter(listOutType);
        addInParameter(inputParam);
        execute();
        return getParameters();
    }

    public ArrayList<DBParameter> doSQL(String query) {
        setDataSource();
        setQuery(query);
        executeSQL();
        return getParameters();
    }


    public void resetParams() {
        this.getParameters().clear();
    }

    private void setDataSource() {
        try {
            connection = AppConfigs.dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Gen JSONObject kết quả từ hàm dùng chung (xml -> json)
     *
     * @return
     */
    private JSONObject getFromXmlResult() {
        try {
            JSONObject object = ((JSONArray) getParamValueAt(0)).getJSONObject(0);
            Iterator<?> keys = object.keys();
            String key = (String) keys.next();
            return XML.toJSONObject(object.getString(key)).getJSONObject("XML");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gen JSONObject từ hàm dùng chung (text_xml -> json)
     */
    private JSONObject getFromXMLStringResult() {
        try {
            JSONObject xmlJSONObj = XML.toJSONObject((String) getParamValueAt(0));
            return xmlJSONObj.getJSONObject("XML");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Lấy dữ liệu array từ kết quả hàm dùng chung
     *
     * @param keyArray
     * @return
     */
    protected JSONArray getJSONArrayFromResultXML(String keyArray) {
        JSONObject jsonObject = null;
        try {
            jsonObject = getFromXmlResult();
            return jsonObject.getJSONArray(keyArray);
        } catch (Exception e) {
            if (jsonObject != null) {
                try {
                    // trường hợp mảng có 1 item (XML.toJSONObject gen ra object thay vì array)
                    JSONObject item = jsonObject.getJSONObject(keyArray);
                    return new JSONArray().put(item);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return new JSONArray();
    }

    public static void main(String[] ar) throws JSONException {

        String abc = "<?xml version=\"1.0\"?><XML><UDT_STYLE><IS_SUCCESS>0</IS_SUCCESS></UDT_STYLE></XML>";
        JSONObject xmlJSONObj = XML.toJSONObject(abc);

        System.out.println(xmlJSONObj.getJSONObject("XML"));
    }

    /**
     * Lấy dữ liệu object từ kết quả hàm dùng chung
     *
     * @param keyObject
     * @return
     */
    protected JSONObject getJSONObjectFromResultXML(String keyObject) {
        try {
            JSONObject jsonObject = getFromXmlResult();
            return jsonObject.getJSONObject(keyObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy dữ liệu object từ kết quả hàm dùng chung
     *
     * @param keyObject
     * @return
     */
    protected JSONObject getJSONObjectFromResultXMLString(String keyObject) {
        try {
            JSONObject jsonObject = getFromXMLStringResult();
            return jsonObject.getJSONObject(keyObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
