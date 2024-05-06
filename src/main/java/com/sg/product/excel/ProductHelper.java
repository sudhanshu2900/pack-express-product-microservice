package com.sg.product.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sg.product.entity.Product;

public class ProductHelper {

    /*
     * These are the attributes of the excel sheet
     */
    public static String[] HEADERS = {
            "Product ID",
            "Product Name",
            "Product Description",
            "Origin",
            "Destination",
            "Estimated Delivery Date",
            "Delivery Status",
            "Request Status"
    };
    /*
     * This sheet name is display on excel sheet
     */
    public static String SHEET_NAME = "Product_Data";
    /*
     * To change Data to Excel format we used ByteArrayInputStream
     */
    public static ByteArrayInputStream dataToExcel(List<Product> list) throws IOException {
        //Create a Workbook
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream finalOutput = null;
        try {
            //Create a Sheet inside Workbook
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            //Create a row in Sheet
            Row row = sheet.createRow(0);
            //Create columns inside a row of sheet
            //First row contain all attributes of Product Table
            for(int i=0; i<HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }
            //Now Values are inserted in reach row
            int rowIndex = 1;
            for(Product p: list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(p.getProductId());
                dataRow.createCell(1).setCellValue(p.getProductName());
                dataRow.createCell(2).setCellValue(p.getProductDescription());
                dataRow.createCell(3).setCellValue(p.getProductOrigin());
                dataRow.createCell(4).setCellValue(p.getProductDestination());
                dataRow.createCell(5).setCellValue(p.getProductEstimatedDeliveryDate());
                dataRow.createCell(6).setCellValue(p.getProductStatus());
                dataRow.createCell(7).setCellValue(p.getReqStatus());
            }
            workbook.write(output);
            finalOutput = new ByteArrayInputStream(output.toByteArray());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally {
            //Here we close our workbook and stop output data
            workbook.close();
            output.close();
        }
        return finalOutput;
    }
}