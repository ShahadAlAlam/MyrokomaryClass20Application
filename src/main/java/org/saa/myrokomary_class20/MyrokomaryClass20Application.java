package org.saa.myrokomary_class20;

import org.saa.myrokomary_class20.config.Configs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"org.saa.myrokomary_class20.entity"})
@EnableJpaRepositories(basePackages={"org.saa.myrokomary_class20.repos"})
public class MyrokomaryClass20Application {

    public static void main(String[] args) {
        Configs.loadConfig();
        SpringApplication.run(MyrokomaryClass20Application.class, args);
    }


}
