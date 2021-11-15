package com.uire.snowhistory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.uire.snowhistory.mapper")
@EnableAsync
public class SnowHistoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnowHistoryApplication.class, args);
    }

}
