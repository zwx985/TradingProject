package com.laoz.trading.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Trading Project Spring Boot Application Entry Point
 * 交易项目 Spring Boot 应用入口
 */
@SpringBootApplication
@MapperScan("com.laoz.trading.project.mapper")
public class TradingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingProjectApplication.class, args);
    }

}
