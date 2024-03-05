package com.springboot.exceltodb.service;

import com.springboot.exceltodb.entity.ExcelEntity;
import com.springboot.exceltodb.filter.ExcelFilter;
import com.springboot.exceltodb.repository.ExcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ExcelService {

    private final ExcelRepository excelRepository;

    @Transactional(rollbackFor = Exception.class)
    public void save(MultipartFile file) {
//      save file to db
        try {
            List<ExcelEntity> entities = ExcelFilter.convertExcelToList(file.getInputStream());
            for (ExcelEntity excelEntity : entities) {
                validateEntity(excelEntity);
            }
            this.excelRepository.saveAll(entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateEntity(ExcelEntity excelEntity) {
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

    public List<ExcelEntity> getAllExcelData() {
        return this.excelRepository.findAll();
    }
}
