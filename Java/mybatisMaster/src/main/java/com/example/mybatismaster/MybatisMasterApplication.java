package com.example.mybatismaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value="com.example.mybatismaster.mapper")
@SpringBootApplication
public class MybatisMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMasterApplication.class, args);
    }

}
