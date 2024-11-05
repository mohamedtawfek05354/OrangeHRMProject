package org.example.ReusePages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtilis {
    //Add data in Excel File
    public void ExcelData(Map<String, List<String>> Data, String ExcelNameFile) throws IOException {
        if (Data.isEmpty()) {
            throw new IllegalArgumentException("The list of data is empty");
        }

        List<String> Columns = new ArrayList<>(Data.keySet());
        int rows = Data.get(Columns.getFirst()).size();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(ExcelNameFile + " Data");
        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int a = 0; a < Columns.size(); a++) {
            headerRow.createCell(a).setCellValue(Columns.get(a));
            System.out.println("Header : "+Columns.get(a));
        }
        // Add data to Excel
        for (int i = 0; i < rows; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < Columns.size(); j++) {
                String columnName = Columns.get(j);
                List<String> columnData = Data.get(columnName);

                if (i < columnData.size()) { // Check if data exists at this index
                    Cell cell = row.createCell(j);
                    cell.setCellValue(columnData.get(i));
                    System.out.println("Row " + (i + 1) + " - Column: " + columnName + " Contains: " + columnData.get(i));
                } else {
                    System.out.println("Skipping cell creation at row " + (i + 1) + " for column " + columnName + " due to missing data.");
                }
            }
        }
        // Write the Excel file to disk
        try (FileOutputStream fileOut = new FileOutputStream("./src/test/resources/" + ExcelNameFile + ".xlsx")) {
            workbook.write(fileOut);
        } catch (Exception e) {
            System.out.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
        workbook.close();
    }
    // Extract data from Excel file
    public Map<String, List<String>> ExtractDataExcel(String ExcelPath) throws IOException {
        Map<String, List<String>> Data = new HashMap<>();

        try (FileInputStream file = new FileInputStream(ExcelPath)){
             Workbook workbook = new XSSFWorkbook(file);

            // Iterate through each sheet in the workbook
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                List<String> sheetData = new ArrayList<>();

                // Iterate through each row in the sheet
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i+1);
                    if (row == null) continue; // Skip empty rows
                    List<String> rowData = new ArrayList<>();
                    // Iterate through each cell in the row
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        String cellValue = (cell != null) ? cell.toString() : ""; // Handle null cells
                        rowData.add(cellValue);
                        sheetData.add(rowData.get(j));
                    }

                    // Add row data to sheet data

                }

                // Store the sheet data in the map using the sheet name as the key
                Data.put(sheet.getSheetName(), sheetData);
            }
        }
        return Data;
    }
//    public void EnterDataInExcel(List<WebElement> productNames, List<WebElement> productPrices,String ExcelFileName) throws IOException {
//
//        // Check if the sizes of both lists are the same
//        if (productNames.size() != productPrices.size()) {
//            throw new IllegalArgumentException("Product names and prices must have the same size.");
//        }
//        Workbook workbook = new XSSFWorkbook();
//        // Create a new Excel workbook
//        try (workbook){
//            Sheet sheet = workbook.createSheet(ExcelFileName+" Data");
//            // Create header row containing ProductName and Price
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("Product Name");
//            headerRow.createCell(1).setCellValue("Price");
//            // Add product names and prices to Excel
//            for (int i = 0; i < productNames.size(); i++) {
//                Row row = sheet.createRow(i + 1);
//                Cell cell1 = row.createCell(0);
//                cell1.setCellValue(productNames.get(i).getText());
//                Cell cell2 = row.createCell(1);
//                cell2.setCellValue(productPrices.get(i).getText());
//            }
//            // Write the Excel file to disk
//            try (FileOutputStream fileOut = new FileOutputStream("./src/test/resources/"+ExcelFileName+".xlsx")) {
//                workbook.write(fileOut);
//            } catch (Exception e) {
//                System.out.println("Error creating the file: " + e.getMessage());
//                e.printStackTrace();
//            }
//        } // Workbook is closed automatically here
//        workbook.close();
//    }

}
