package org.saa.myrokomary_class20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean(name = "mainDataSource")
    @Primary
    public DataSource mainDataSource()  {
        DataSource dbSource = new DriverManagerDataSource(Configs.getConfig_db_url(),
                Configs.getConfig_db_user(),
                Configs.getConfig_db_password());

        return dbSource;

    }

}
