package com.springboot.exceltodb.filter;

import com.springboot.exceltodb.entity.ExcelEntity;

public class CheckData {
    public static void validateEntity(ExcelEntity excelEntity) {
        if (excelEntity.getFirstName() == null || excelEntity.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty for entity: " + excelEntity.getId());
        }
        if (excelEntity.getAge() == 0) {
            throw new IllegalArgumentException("Name cannot be empty for entity: " + excelEntity.getId());
        }
        if (excelEntity.getLastName() == null || excelEntity.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty for entity: " + excelEntity.getId());
        }
        if (excelEntity.getCountry() == null || excelEntity.getCountry().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty for entity: " + excelEntity.getId());
        }
        if (excelEntity.getGender() == null || excelEntity.getGender().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty for entity: " + excelEntity.getId());
        }
    }
}
