package org.saa.myrokomary_class20.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class Configs {
//    spring.datasource.url=jdbc:postgresql://localhost:5432/<YOUR_DATABASE_NAME>
//    spring.datasource.username=<YOUR_USERNAME>
//    spring.datasource.password=<YOUR_PASSWORD>
//    spring.jpa.hibernate.ddl-auto=<create | create-drop | update | validate | none>
//    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
//    spring.jpa.generate-ddl = true
    @Value("spring.datasource.url")
    private static String config_db_url="jdbc:postgresql://localhost:5432/postgres";

    @Value("spring.datasource.username")
    private static String config_db_user="postgres";

    @Value("spring.datasource.password")
    private static String config_db_password="SYSTEM11g";

    @Value("spring.jpa.hibernate.ddl-auto")
    private static String config_hibernate_ddl_auto="create-drop";

    @Value("spring.jpa.properties.hibernate.dialect")
    private static String config_hibernate_dialect="org.hibernate.dialect.PostgreSQLDialect";

    @Value("spring.jpa.generate-ddl")
    private static String config_jpa_generate_ddl="true";

    public static void loadConfig(){
        config_db_url=getEnvValue("config_db_url", "jdbc:postgresql://localhost:5432/postgres");

        config_db_user=getEnvValue("config_db_user","postgres");

        config_db_password=getEnvValue("config_db_password","SYSTEM11g");

        config_hibernate_ddl_auto=getEnvValue("config_hibernate_ddl_auto","validate");// "create-drop";

        config_hibernate_dialect=getEnvValue("config_hibernate_dialect","org.hibernate.dialect.PostgreSQLDialect");

        config_jpa_generate_ddl=getEnvValue("config_jpa_generate_ddl","true");

    }

    private static String getEnvValue(String key, String defaultValue){
        if(!System.getenv(key).equals("")){
            return System.getenv(key);
        } else {
            return defaultValue;
        }
    }
    public static String getConfig_db_url() {
        return config_db_url;
    }

    public static void setConfig_db_url(String config_db_url) {
        Configs.config_db_url = config_db_url;
    }

    public static String getConfig_db_user() {
        return config_db_user;
    }

    public static void setConfig_db_user(String config_db_user) {
        Configs.config_db_user = config_db_user;
    }

    public static String getConfig_db_password() {
        return config_db_password;
    }

    public static void setConfig_db_password(String config_db_password) {
        Configs.config_db_password = config_db_password;
    }

    public static String getConfig_hibernate_ddl_auto() {
        return config_hibernate_ddl_auto;
    }

    public static void setConfig_hibernate_ddl_auto(String config_hibernate_ddl_auto) {
        Configs.config_hibernate_ddl_auto = config_hibernate_ddl_auto;
    }
}
