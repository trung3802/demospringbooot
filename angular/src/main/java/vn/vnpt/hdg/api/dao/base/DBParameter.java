package vn.vnpt.hdg.api.dao.base;

import lombok.Data;

@Data
public class DBParameter {
    private int index = -1;

    private boolean outParameter = false;

    private Object value = null;

    private Integer type = null;

    public static DBParameter createOutParameter(int type) {
        DBParameter outParam = new DBParameter();
        outParam.setType(type);
        outParam.setOutParameter(true);
        return outParam;
    }
}
