package org.saa.myrokomary_class20;

import org.saa.myrokomary_class20.config.Configs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages={"org.saa.myrokomary_class20.*"})
@ComponentScan(basePackages={"org.saa.myrokomary_class20.*"})
@EnableJpaRepositories(basePackages={"org.saa.myrokomary_class20.*"})
@EnableWebMvc //if this is not added then interceptor will give error class not found
public class MyrokomaryClass20Application {

    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(MyrokomaryClass20Application.class);
//        Configs.loadConfig();
////        Environment env = app.run(args).getEnvironment();
//        app.run(args);
        SpringApplication.run(MyrokomaryClass20Application.class,args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

//            @Autowired
//            private SecValidateorInterceptor secValidateorInterceptor;
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(Configs.getAllowedRestApiMethodes())
//                        .allowedMethods("GET","POST")
                        .allowedOrigins("*");
            }

//            @Override
//            public void addInterceptors(InterceptorRegistry registry){
//                registry.addInterceptor(secValidateorInterceptor);
//            }
        };

    }



}
