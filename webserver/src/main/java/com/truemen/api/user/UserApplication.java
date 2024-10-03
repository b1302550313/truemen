package com.truemen.api.user;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.truemen.api.user", "com.truemen.api.common" })
public class UserApplication {
    // public static void main(String[] args) {
    // System.out.println("Hello world!");
    // }
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}