package com.springboot.exceltodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ExcelToDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelToDbApplication.class, args);
    }

}
