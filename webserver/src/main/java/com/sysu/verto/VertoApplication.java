package com.sysu.verto;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackages = {"com.sysu.verto.dao"}, annotationClass = Mapper.class)
//@MapperScan(basePackages = "com.sysu.verto.dao")
@SpringBootApplication
public class VertoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VertoApplication.class, args);
	}

}
