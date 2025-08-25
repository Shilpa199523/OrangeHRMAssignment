package com.orangehrm.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    private static final String TEST_DATA_PATH = "src/test/resources/testdata/TestData.xlsx";
    private static final String Test_Data_EMP ="src/test/resources/testdata/EmpData.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;

    public static Object[][] getLoginData() {
        try {
            FileInputStream fis = new FileInputStream(TEST_DATA_PATH);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet("LoginData");

            int rowCount = sheet.getLastRowNum();
            Object[][] data = new Object[rowCount][2];

            // Start from row 1 as row 0 is header
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                data[i-1][0] = row.getCell(0).getStringCellValue(); // username
                data[i-1][1] = row.getCell(1).getStringCellValue(); // password
            }

            workbook.close();
            fis.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from Excel: " + e.getMessage());
        }
    }

    public static Map<String, String> getLoginCredentials(String testCase) {
        try {
            FileInputStream fis = new FileInputStream(TEST_DATA_PATH);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet("Credentials");

            Map<String, String> credentials = new HashMap<>();

            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equals(testCase)) {
                    credentials.put("username", row.getCell(1).getStringCellValue());
                    credentials.put("password", row.getCell(2).getStringCellValue());
                    break;
                }
            }

            workbook.close();
            fis.close();
            return credentials;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from Excel: " + e.getMessage());
        }
    }
    
    public static Object[][] getEmpData() {
        try {
            FileInputStream fis1 = new FileInputStream(Test_Data_EMP);
            workbook = new XSSFWorkbook(fis1);
            sheet = workbook.getSheet("Sheet1");

            int rowCount = sheet.getLastRowNum();//number of rows
            int colCount = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rowCount][colCount];

            // Start from row 1 as row 0 is header
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for(int j=0;j<colCount; j++) {
                data[i-1][j] = row.getCell(j).toString(); // 
                
            }
            }

            workbook.close();
            fis1.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test data from Excel: " + e.getMessage());
        }
    }
  
    
    
}
