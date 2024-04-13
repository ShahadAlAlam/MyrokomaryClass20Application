package org.saa.myrokomary_class20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Autowired
    private Environment env;
    @Bean(name = "mainDataSource")
    @Primary
    public DataSource mainDataSource()  {
//        DataSource dbSource = new DriverManagerDataSource(Configs.getConfig_db_url(),
//                Configs.getConfig_db_user(),
//                Configs.getConfig_db_password());

/* working */
        DriverManagerDataSource  dbSource = new DriverManagerDataSource();
        Configs.build();
        dbSource.setDriverClassName(Configs.getConfig_db_driver());// env.getProperty("spring.datasource.driver-class-name"));
        dbSource.setUrl(Configs.getConfig_db_url());//env.getProperty("spring.datasource.url"));
        dbSource.setUsername(Configs.getConfig_db_user());//env.getProperty("spring.datasource.username"));
        dbSource.setPassword(Configs.getConfig_db_password());//env.getProperty("spring.datasource.password"));
        dbSource.setSchema(Configs.getDb_default_schema());//env.getProperty("accounting"));
        dbSource.setConnectionProperties(Configs.getConProps());

        return dbSource;

    }

}
