package com.springboot.exceltodb.controller;

import com.springboot.exceltodb.entity.ExcelEntity;
import com.springboot.exceltodb.filter.ExcelFilter;
import com.springboot.exceltodb.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {
    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (ExcelFilter.checkExcelFormat(file)) {
            this.excelService.save(file);
            return ResponseEntity.ok(Map.of("Message", "File Saved"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This is not a Excel File");
    }

    @GetMapping("/viewExcel")
    public List<ExcelEntity> geAllData() {
        return this.excelService.getAllExcelData();
    }

}
