package com.sg.product.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.product.entity.Product;
import com.sg.product.exception.ProductNotFoundException;
import com.sg.product.pojo.StatusModel;
import com.sg.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

/*
 * ProductController is a RestController that handle the API request coming from client and invoke appropriate business method
 * author: Sudhanshu Gupta
 */

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(value = "http://localhost:3000/")
public class ProductController {

    @Autowired
    ProductService productService;
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    /*
     * addProduct(): It add a new Product into the system
     */
    @PostMapping("/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.info("productController: addProduct() method invoke");
        Product item = productService.createProduct(product);
        logger.info("productController: addProduct() method end");
        return new ResponseEntity<Product>(item, HttpStatus.OK);
    }
    /*
     * viewAllRequestedProducts(): Get all the Products that need to Approve or Reject
     */
    @GetMapping("/allrequestedproducts")
    public ResponseEntity<List<Product>> viewAllRequestedProducts(){
        logger.info("productController: viewAllRequestedProducts() method invoke");
        List<Product> productList = productService.getAllRequestedProducts();
        logger.info("productController: viewAllRequstedProducts() method end");
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
    /*
     * viewAllApprovedProducts(): Get all the Approved products
     */
    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> viewAllApprovedProducts(){
        logger.info("productController: viewAllApprovedProducts() method invoke");
        List<Product> productList = productService.getAllApprovedProducts();
        logger.info("productController: viewAllApprovedProducts() method end");
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
    /*
     * viewAllCustomerProducts(): Get all the products that are associate with customer_id
     */
    @GetMapping("/allcustproducts/{cust_id}")
    public ResponseEntity<List<Product>> viewAllCustomerProducts(@PathVariable int cust_id){
        logger.info("productController: viewAllCustomerProducts() method invoke");
        List<Product> productList = productService.getAllProductsByCustomerId(cust_id);
        logger.info("productController: viewAllCustomerProducts() method end");
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
    /*
     * viewProductById(): Get a product by its product_id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> viewProductById(@PathVariable int id) throws ProductNotFoundException{
        logger.info("productController: viewProductById() method invoke");
        Product product = productService.getProductById(id);
        logger.info("productController: viewProductById() method end");
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    /*
     * updateProduct(): Edit the product details by its product_id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id) throws ProductNotFoundException{
        logger.info("productController: updateProduct() method invoke");
        Product item = productService.editProduct(product, id);
        logger.info("productController: updateProduct() method end");
        return new ResponseEntity<Product>(item, HttpStatus.OK);
    }
    /*
     * updateProductStatus() :  Edit the status of product (Deliver Status, Product Delay)
     */
    @PutMapping("/updatestatus/{id}")
    public ResponseEntity<Product> updateProductStatus(@RequestBody StatusModel model, @PathVariable int id) throws ProductNotFoundException{
        logger.info("productController: updateProduct() method invoke");
        Product item = productService.editProduct(model, id);
        logger.info("productController: updateProduct() method end");
        return new ResponseEntity<Product>(item, HttpStatus.OK);
    }
    /*
     * updateProduct(): Update the product status (Approve or Reject) that are associated with its customer_id
     */
    @PutMapping("/update/{id}/{reqStatus}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @PathVariable String reqStatus) throws ProductNotFoundException{
        logger.info("productController: updateRequestStatus() method invoke");
        Product item = productService.editRequestStatus(id, reqStatus);
        logger.info("productController: updateRequestStatus() method end");
        return new ResponseEntity<Product>(item, HttpStatus.OK);
    }
    /*
     * deleteProduct(): Delete a product with its product_id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) throws ProductNotFoundException {
        logger.info("productController: deleteProduct() method invoke");
        productService.deleteProduct(id);
        logger.info("productController: deleteProduct() method end");
    }

}