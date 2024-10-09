package com.truemen.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 请在测试没问题后再添加扫描的包，方便他人运行不报错。
@SpringBootApplication()
@ComponentScan(basePackages = {"com.truemen.api.common","com.truemen.api.post"})
public class TruemenApplication {
    public static void main(String[] args) {
        SpringApplication.run(TruemenApplication.class, args);
    }
}