package com.laoz.trading.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.laoz.trading.project.mapper")
public class TradingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingProjectApplication.class, args);
    }

}
