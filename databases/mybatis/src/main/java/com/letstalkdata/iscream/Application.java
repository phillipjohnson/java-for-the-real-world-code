package com.letstalkdata.iscream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.letstalkdata.iscream.service")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
