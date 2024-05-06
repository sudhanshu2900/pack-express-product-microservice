package com.sg.product.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.product.service.ExcelService;

/*
 * ExcelController is a RestController that handle the API request coming from client and invoke appropriate business method
 * author: Sudhanshu Gupta
 */

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping("/api/v1/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;
    Logger logger = LoggerFactory.getLogger(ExcelController.class);
    /*
     * downloadProductExcel(): This method download an excel file of name 'product.xlsx' that contains information of all the products
     */

    @GetMapping("/products")
    public ResponseEntity<Resource> downloadProductExcel() throws IOException {
        logger.info("excelController: downloadProductExcel() method invoke");
        String filename = "Products.xlsx";
        ByteArrayInputStream actualData = excelService.getAllProductsInExcelFormat();
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        logger.info("excelController: downloadProductExcel() method end");
        return body;
    }
    /*
     * downloadRejectedRequestExcel(): This method download an excel file of name 'Rejected_Products.xlsx' that contains information of all the rejected products
     */
    @GetMapping("/rejectedrequests")
    public ResponseEntity<Resource> downloadRejectedRequestExcel() throws IOException {
        logger.info("excelController: downloadRejectedRequestExcel() method invoke");
        String filename = "Rejected_Products.xlsx";
        ByteArrayInputStream actualData = excelService.getAllRejectedProductsInExcelFormat();
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        logger.info("excelController: downloadRejectedRequestExcel() method end");
        return body;
    }
    /*
     * downloadInTransitProductExcel(): This method download an excel file of name 'In-Transit_Products.xlsx' that contains information of all the In-Transit products
     */
    @GetMapping("/intransit")
    public ResponseEntity<Resource> downloadInTransitProductExcel() throws IOException {
        logger.info("excelController: downloadInTransitProductExcel() method invoke");
        String filename = "In-Transit_Products.xlsx";
        ByteArrayInputStream actualData = excelService.getInTransitProductsInExcelFormat();
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        logger.info("excelController: downloadInTransitProductExcel() method end");
        return body;
    }
    /*
     * downloadDeliveredProductExcel(): This method download an excel file of name 'Delivered_Products.xlsx' that contains information of all the delivered products
     */
    @GetMapping("/delivered")
    public ResponseEntity<Resource> downloadDeliveredProductExcel() throws IOException {
        logger.info("excelController: downloadDeliveredProductExcel() method invoke");
        String filename = "Delivered_Products.xlsx";
        ByteArrayInputStream actualData = excelService.getAllDeliveredProductsInExcelFormat();
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        logger.info("excelController: downloadDeliveredProductExcel() method end");
        return body;
    }
    /*
     * downloadProductWithCustIdExcel(): This method download an excel file of name 'Products_of_Customer.xlsx' that contains information of all the products
     * that are associated with specific customer
     */
    @GetMapping("/custproducts/{id}")
    public ResponseEntity<Resource> downloadProductWithCustIdExcel(@PathVariable int id) throws IOException {
        logger.info("excelController: downloadProductWithCustIdExcel() method invoke");
        String filename = "Products_of_Customer.xlsx";
        ByteArrayInputStream actualData = excelService.getAllProductsWithCustIdInExcelFormat(id);
        InputStreamResource file = new InputStreamResource(actualData);

        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        logger.info("excelController: downloadProductWithCustIdExcel() method end");
        return body;
    }

}