package com.sg.product.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.product.entity.Product;
import com.sg.product.excel.ProductHelper;
import com.sg.product.repository.ProductRepository;
import com.sg.product.service.ExcelService;

/*
 * Excel Service class the handle the business logic of excel part
 * This class implements ExcelService Interface
 */


@Service
public class ExcelServiceImpl implements ExcelService{
    @Autowired
    ProductRepository productRepository;
    Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
    /*
     * getAllProductsInExcelFormat() : Get attributes of all existing products
     */

    @Override
    public ByteArrayInputStream getAllProductsInExcelFormat() throws IOException {
        logger.info("ExcelService: getAllProductsInExcelFormat() method invoke");
        List<Product> productList = productRepository.findAll();
        ByteArrayInputStream inputStream = ProductHelper.dataToExcel(productList);
        logger.info("ExcelService: getAllProductsInExcelFormat() method end");
        return inputStream;
    }
    /*
     * getAllRejectedProductsInExcelFormat() : Get all the products that are rejected by Admin
     */

    @Override
    public ByteArrayInputStream getAllRejectedProductsInExcelFormat() throws IOException {
        logger.info("ExcelService: getAllRejectedProductsInExcelFormat() method invoke");
        List<Product> productList = productRepository.findAllRejectedProducts();
        ByteArrayInputStream inputStream = ProductHelper.dataToExcel(productList);
        logger.info("ExcelService: getAllRejectedProductsInExcelFormat() method end");
        return inputStream;
    }
    /*
     * getInTransitProductsInExcelFormat() : Get all the products that are in In-Transit state of delivery
     */
    @Override
    public ByteArrayInputStream getInTransitProductsInExcelFormat() throws IOException {
        logger.info("ExcelService: getInTransitProductsInExcelFormat() method invoke");
        List<Product> productList = productRepository.findAllInTransitProducts();
        ByteArrayInputStream inputStream = ProductHelper.dataToExcel(productList);
        logger.info("ExcelService: getInTransitProductsInExcelFormat() method end");
        return inputStream;
    }
    /*
     * getAllDeliveredProductsInExcelFormat() : Get all the products that are already delivered
     */

    @Override
    public ByteArrayInputStream getAllDeliveredProductsInExcelFormat() throws IOException {
        logger.info("ExcelService: getAllDeliveredProductsInExcelFormat() method invoke");
        List<Product> productList = productRepository.findAllDeliveredProducts();
        ByteArrayInputStream inputStream = ProductHelper.dataToExcel(productList);
        logger.info("ExcelService: getAllDeliveredProductsInExcelFormat() method end");
        return inputStream;
    }
    /*
     * getAllProductsWithCustIdInExcelFormat() : Get all the products of specific customer with its customer_id
     */

    @Override
    public ByteArrayInputStream getAllProductsWithCustIdInExcelFormat(int id) throws IOException {
        logger.info("ExcelService: getAllProductsWithCustIdInExcelFormat() method invoke");
        List<Product> productList = productRepository.findAllProductsAssociatedWithCustomerId(id);
        ByteArrayInputStream inputStream = ProductHelper.dataToExcel(productList);
        logger.info("ExcelService: getAllProductsWithCustIdInExcelFormat() method end");
        return inputStream;
    }
}