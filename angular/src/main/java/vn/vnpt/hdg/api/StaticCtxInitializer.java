package vn.vnpt.hdg.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.vnpt.hdg.api.configs.AppConfigs;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Component
public class StaticCtxInitializer {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void init() {
        System.out.println("Init App Configs...");
        AppConfigs.dataSource = dataSource;
    }
}
