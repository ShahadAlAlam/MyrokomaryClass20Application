package org.saa.myrokomary_class20.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class Configs {
    Configs(){
        build();
    }

    @Value("spring.datasource.url")
    private static String config_db_url;
    @Value("spring.datasource.username")
    private static String config_db_user;
    @Value("spring.datasource.password")
    private static String config_db_password;

    @Value("spring.datasource.driver-class-name")
    private static String config_db_driver;

    @Value("spring.jpa.properties.hibernate.dialect")
    private static String config_hibernate_dialect;

//  Common Configuration
    @Value("spring.jpa.hibernate.ddl-auto")
//    private static String config_hibernate_ddl_auto;
    private static String config_hibernate_ddl_auto="create-drop";
    @Value("spring.jpa.generate-ddl")
//    private static String config_jpa_generate_ddl;
    private static String config_jpa_generate_ddl="true";


    @Value("${app.dbType}")
    private static String db_db_type="POSTGRESQL";

    @Value("spring.jpa.properties.hibernate.default_schema")
    private static String db_default_schema;
//    private static String db_default_schema="rokomary";

    private static Properties conProps = new Properties();

    private static String[] allowedRestApiMethodes = {"GET","POST","PUT","DELETE"};
    public static void build(){
//        db_db_type="POSTGRESQL";
//        if(AppProperties.getDbType().equalsIgnoreCase("POSTGRESQL")) {
        if(db_db_type.equalsIgnoreCase("POSTGRESQL")) {
            config_db_url="jdbc:postgresql://192.168.1.222:5432/postgres";
            config_db_user="postgres";
            config_db_password="SYSTEM11g";
            config_jpa_generate_ddl = "true";
//            config_hibernate_ddl_auto="validate";// "create-drop";
            config_hibernate_ddl_auto="create-drop";
            config_hibernate_dialect="org.hibernate.dialect.PostgreSQLDialect";
            config_db_driver = "org.postgresql.Driver";
            db_default_schema="rokomary";

            conProps.setProperty("url","jdbc:postgresql://localhost:5432/postgres");
            conProps.setProperty("username","postgres");
            conProps.setProperty("password","SYSTEM11g");
            conProps.setProperty("spring.jpa.properties.hibernate.dialect",
                    "org.hibernate.dialect.PostgreSQLDialect");
            conProps.setProperty("schema","rokomary");
            conProps.setProperty("spring.jpa.hibernate.ddl-auto","create-drop");
            conProps.setProperty("spring.jpa.generate-ddl","true");
            conProps.setProperty("driverClassName","org.postgresql.Driver");
        }
        else {
            config_db_url="jdbc:oracle:thin:@//localhost:1521/TTT";
            config_db_user="FERPN";
            config_db_password="TTI";
            config_hibernate_ddl_auto="create-drop";
            config_hibernate_dialect="org.hibernate.dialect.Oracle12cDialect";
            db_default_schema="FERPN";
            config_db_driver = "oracle.jdbc.OracleDriver";

            conProps.setProperty("url","jdbc:oracle:thin:@//localhost:1521/TTT");
            conProps.setProperty("username","FERPN");
            conProps.setProperty("password","TTI");
            conProps.setProperty("spring.jpa.properties.hibernate.dialect",
                    "org.hibernate.dialect.Oracle12cDialect");
            conProps.setProperty("spring.jpa.hibernate.ddl-auto","create-drop");
            conProps.setProperty("spring.jpa.generate-ddl","true");
            conProps.setProperty("spring.jpa.properties.hibernate.default_schema","FERPN");
            conProps.setProperty("driverClassName","oracle.jdbc.OracleDriver");
        }

        config_jpa_generate_ddl="true";


    }

    public static void loadConfig(){
        build();
    }

    public static String getConfig_db_driver() {
        return config_db_driver;
    }

    public static void setConfig_db_driver(String config_db_driver) {
        Configs.config_db_driver = config_db_driver;
    }

    public static Properties getConProps() {
        return conProps;
    }

    public static void setConProps(Properties conProps) {
        Configs.conProps = conProps;
    }

    public static String getDb_default_schema() {
        return db_default_schema;
    }

    public static void setDb_default_schema(String db_default_schema) {
        Configs.db_default_schema = db_default_schema;
    }

    private static String getEnvValue(String key, String defaultValue){
        if(key.length()>0){
                return System.getenv(key);
        }
        else
            return defaultValue;
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

    public static String getConfig_hibernate_dialect() {
        return config_hibernate_dialect;
    }

    public static void setConfig_hibernate_dialect(String config_hibernate_dialect) {
        Configs.config_hibernate_dialect = config_hibernate_dialect;
    }

    public static String[] getAllowedRestApiMethodes() {
        return allowedRestApiMethodes;
    }
}
