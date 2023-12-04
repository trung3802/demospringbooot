package vn.vnpt.hdg.api.configs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "fileserver")

public class FileConfig {
    @Value("${fileserver.envDev}")
    private boolean envDev;

    @Value("${fileserver.fileUrlDev}")
    private String fileUrlDev;

    @Value("${fileserver.rootFolderDev}")
    private String rootFolderDev;

    @Value("${fileserver.envProd}")
    private boolean envProd;

    @Value("${fileserver.fileUrlProd}")
    private String fileUrlProd;

    @Value("${fileserver.rootFolderProd}")
    private String rootFolderProd;
}