package com.springboot.exceltodb.service;

import com.springboot.exceltodb.entity.ExcelEntity;
import com.springboot.exceltodb.filter.DataFilter;
import com.springboot.exceltodb.filter.ExcelFilter;
import com.springboot.exceltodb.repository.ExcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static com.springboot.exceltodb.filter.CheckData.validateEntity;


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


    public List<ExcelEntity> getAllExcelData() {
        return this.excelRepository.findAll();
    }

    public ByteArrayInputStream downloadExcel() throws IOException {
        List<ExcelEntity> all = excelRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = DataFilter.dataToExcel(all);
        return byteArrayInputStream;
    }
}
