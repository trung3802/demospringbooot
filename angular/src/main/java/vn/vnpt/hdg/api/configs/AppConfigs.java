package vn.vnpt.hdg.api.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Data
@Component
public class AppConfigs {
    public static DataSource dataSource;

    @Value("${app.defaultPassword}")
    private String defaultPassword;
}
