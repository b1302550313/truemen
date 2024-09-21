package com.truemen.api.post;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PostApplication {
    // public static void main(String[] args) {
    // System.out.println("Hello world!");
    // }
    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }
}