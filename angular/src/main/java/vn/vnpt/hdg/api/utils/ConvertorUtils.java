package vn.vnpt.hdg.api.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Utility for converting ResultSets into some Output formats
 */
public class ConvertorUtils {

    /**
     * Convert a result set into a JSON Array
     *
     * @param resultSet
     * @return a JSONArray
     * @throws SQLException
     * @throws JSONException
     * @throws Exception
     */
    public static JSONArray convertResultSetIntoJSON(ResultSet resultSet)
            throws SQLException, JSONException {
        JSONArray jsonArray = new JSONArray();
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    int total_rows = resultSet.getMetaData().getColumnCount();
                    JSONObject obj = new JSONObject();
                    for (int i = 0; i < total_rows; i++) {
                        String columnName = resultSet.getMetaData()
                                .getColumnLabel(i + 1).toLowerCase();
                        Object columnValue = resultSet.getObject(i + 1);
                        if (columnValue instanceof Clob) {
                            columnValue = getClob((Clob) columnValue);
                        } else if (columnValue instanceof ResultSet) {
                            columnValue = convertResultSetIntoJSON((ResultSet) columnValue);
                        }
                        // if value in DB is null, then we set it to default value
                        if (columnValue == null) {
                            columnValue = "";
                        }
                        /*
                         * Next if block is a hack. In case when in db we have
                         * values like price and price1 there's a bug in jdbc - both
                         * this names are getting stored as price in ResulSet.
                         * Therefore when we store second column value, we overwrite
                         * original value of price. To avoid that, i simply add 1 to
                         * be consistent with DB.
                         */
                        if (obj.has(columnName)) {
                            columnName += "1";
                        }
                        obj.put(StringExtUtils.convertSnakeToCamel(columnName.toLowerCase()), columnValue);
                    }
                    jsonArray.put(obj);
                }
            }
        } finally {
            // Close here for recursive
            resultSet.close();
        }

        return jsonArray;
    }

    public static int converBooleanIntoInt(boolean bool) {
        if (bool)
            return 1;
        else
            return 0;
    }

    public static int convertBooleanStringIntoInt(String bool) {
        if (bool.equals("false"))
            return 0;
        else if (bool.equals("true"))
            return 1;
        else {
            throw new IllegalArgumentException(
                    "wrong value is passed to the method. Value is " + bool);
        }
    }

    public static double getDoubleOutOfString(String value, String format,
                                              Locale locale) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat(format, otherSymbols);
        String formattedValue = f.format(Double.parseDouble(value));
        double number = Double.parseDouble(formattedValue);
        return Math.round(number * 100.0) / 100.0;
    }

    public static String convertXmlToJson(String xml) {
        try {
            return XML.toJSONObject(xml).toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    // Close stream t
    public static String getClob(Clob clob) {
        Reader is = null;
        char[] buffer;
        int count = 0;
        int length = 0;
        String data = null;
        String[] type;
        StringBuffer sb;
        try {
            if (clob == null) {
                return "";
            }

            StringBuffer ret = new StringBuffer();
            is = clob.getCharacterStream();
            if (is == null) {
                return null;
            } else {
                length = (int) clob.length();
                buffer = new char[length];
                count = 0;
                try {
                    while ((count = is.read(buffer)) != -1)
                        ret.append(buffer);
                } catch (Exception e) {
                }

                return ret.toString();
            }
        } catch (Exception e) {

            e.printStackTrace();
            return "";
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

}
