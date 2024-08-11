package com.platzi.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PlatziMarketApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(PlatziMarketApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
