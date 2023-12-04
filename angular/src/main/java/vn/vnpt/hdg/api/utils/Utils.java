package vn.vnpt.hdg.api.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.TimeZone;

public class Utils {
    public static ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        return om;
    }
}
