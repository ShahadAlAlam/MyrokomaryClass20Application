package org.saa.myrokomary_class20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class AppProperties {

    private static Environment env;


    public AppProperties(Environment enve) {
        env = enve;
    }

    public static String getDbType() {
        String schema = env.getProperty("app.dbType");
        System.out.println("Schema value: " + schema);
        return schema;
    }
}
