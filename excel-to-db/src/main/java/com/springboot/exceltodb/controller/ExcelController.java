package com.springboot.exceltodb.controller;

import com.springboot.exceltodb.entity.ExcelEntity;
import com.springboot.exceltodb.filter.ExcelFilter;
import com.springboot.exceltodb.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @RequestMapping("/download")
    public ResponseEntity<Resource> downloadExcel() throws IOException {
        String fileName = "data.xlsx";
        ByteArrayInputStream actualData = excelService.downloadExcel();
        InputStreamResource file = new InputStreamResource(actualData);
        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(file);
        return body;
    }

}
