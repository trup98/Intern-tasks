package com.springboot.exceltodb;

import com.springboot.exceltodb.repository.ExcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExcelToDbApplicationTests {

    @Autowired
    private ExcelRepository excelRepository;
    @Test
    void contextLoads() {
    }

    @Test
    public void testExcelData(){
        excelRepository.findAll().forEach(firstName-> System.out.println(firstName.getFirstName()));
    }
}
