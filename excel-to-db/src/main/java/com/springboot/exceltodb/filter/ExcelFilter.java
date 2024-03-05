package com.springboot.exceltodb.filter;

import com.springboot.exceltodb.entity.ExcelEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelFilter {
    //    check the file is excel type or not
    public static Boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    //    converts excel file to list
    public static List<ExcelEntity> convertExcelToList(InputStream inputStream) {
        List<ExcelEntity> entities = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cid = 0;
                ExcelEntity excelEntity = new ExcelEntity();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cid) {
                        case 0:
                            excelEntity.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            excelEntity.setFirstName(cell.getStringCellValue());
                            break;
                        case 2:
                            excelEntity.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            excelEntity.setGender(cell.getStringCellValue());
                            break;
                        case 4:
                            excelEntity.setCountry(cell.getStringCellValue());
                            break;
                        case 5:
                            excelEntity.setAge((int) cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                entities.add(excelEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
}

