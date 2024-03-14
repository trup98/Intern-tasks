package com.springmvc.example;

import com.springmvc.example.model.DemoVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringCoreApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringCoreApplication.class, args);
        DemoVo demoVo = context.getBean(DemoVo.class);
        demoVo.setName("Rao");
        demoVo.setRollNo(153);
        System.out.println("Name:   " + demoVo.getName());
        System.out.println("Roll No: " + demoVo.getRollNo());
    }

}
