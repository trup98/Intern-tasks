package com.springmvc.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
public class DemoVo {
    private String name;
    private int rollNo;
}
