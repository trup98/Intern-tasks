package com.springboot.exceltodb.filter;

import com.springboot.exceltodb.entity.ExcelEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DataFilter {

    public static String[] HEADER = {"Id", "First Name", "Last Name", "Gender", "Country", "Age"};
    public static String SHEET_NAME = "data";

    public static ByteArrayInputStream dataToExcel(List<ExcelEntity> list) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
//            create Row
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADER.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADER[i]);
            }
//            value row
            int rowIndex = 1;
            for (ExcelEntity excel : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(excel.getId());
                dataRow.createCell(1).setCellValue(excel.getFirstName());
                dataRow.createCell(2).setCellValue(excel.getLastName());
                dataRow.createCell(3).setCellValue(excel.getGender());
                dataRow.createCell(4).setCellValue(excel.getCountry());
                dataRow.createCell(5).setCellValue(excel.getAge());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to import excel");
            return null;
        } finally {
            workbook.close();
            out.close();
        }
    }

}
