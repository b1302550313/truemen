package com.truemen.api.chat;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ChatApplication {
    // public static void main(String[] args) {
    // System.out.println("Hello world!");
    // }
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}