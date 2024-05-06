package com.sg.product.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.product.controller.ProductController;
import com.sg.product.entity.Order;
import com.sg.product.entity.OrderStatus;
import com.sg.product.entity.Product;
import com.sg.product.exception.ProductNotFoundException;
import com.sg.product.pojo.StatusModel;
import com.sg.product.repository.OrderRepository;
import com.sg.product.repository.OrderStatusRepository;
import com.sg.product.repository.ProductRepository;
import com.sg.product.service.OrderService;
import com.sg.product.service.OrderStatusService;
import com.sg.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

/*
 * Product Service class that handles the business logic.
 * This class implements ProductService Interface
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    OrderStatusService orderStatusService;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    /*
     * createProduct() : Create a new product and save into database
     */

    @Override
    public Product createProduct(Product product) {
        logger.info("productService: createProduct() method invoke");
        Product item = productRepository.save(product);
        logger.info("productService: createProduct() method end");
        return item;
    }
    /*
     * getAllRequestedProducts() : Display all the products that are requested by user to into the database
     */
    @Override
    public List<Product> getAllRequestedProducts() {
        logger.info("productService: getAllRequestedProducts() method invoke");
        List<Product> productList =  productRepository.findAllReqProducts();
        logger.info("productService: getAllRequestedProducts() method end");
        return productList;
    }
    /*
     * getAllProducts() : Display all the existing approved products of the database
     */

    @Override
    public List<Product> getAllApprovedProducts() {
        logger.info("productService: getAllProducts() method invoke");
        List<Product> productList =  productRepository.findAllApprovedProducts();
        logger.info("productService: getAllProducts() method end");
        return productList;
    }
    /*
     * getAllProductsByCustomerId() : Display all products that are associated with given Customer_ID from database
     */
    @Override
    public List<Product> getAllProductsByCustomerId(int cust_id) {
        logger.info("productService: getAllProductsByCustomerId() method invoke");
        List<Product> productList = productRepository.findAllProductsAssociatedWithCustomerId(cust_id);
        logger.info("productService: getAllProductsByCustomerId() method end");
        return productList;
    }

    /*
     * getProductById() : Display a product with given ID from database
     */
    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        logger.info("productService: getProductById() method invoke");
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException("Product with ID: " +id+ " not found");
        }
        logger.info("productService: getProductById() method end");
        return product;
    }
    /*
     *  editProduct() : Update the product details based on associated ID
     */
    @Override
    public Product editProduct(Product product, int id) throws ProductNotFoundException {
        logger.info("productService: editProduct() method invoke");
        Product item = productRepository.findById(id).orElse(null);
        if(item == null) {
            throw new ProductNotFoundException("Product with ID: " +id+ " not found");
        }
        item.setProductName(product.getProductName());
        item.setProductDescription(product.getProductDescription());
        item.setProductOrigin(product.getProductOrigin());
        item.setProductDestination(product.getProductDestination());
//		item.setProductStatus(product.getProductStatus());
//		item.setProductDelay(product.getProductDelay());
        item.getOrder().setProductName(product.getProductName());
        item.getOrder().setProductDescription(product.getProductDescription());
        item.getOrder().setProductOrigin(product.getProductOrigin());
        item.getOrder().setProductDestination(product.getProductDestination());

        logger.info("productService: editProduct() method end");
        return productRepository.save(item);
    }
    @Override
    public Product editProduct(StatusModel model, int id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException("Product with ID: " +id+ " not found");
        }
        product.setProductStatus(model.getProductStatus());
        product.setProductDelay(model.getProductDelay());
        product = productRepository.save(product);
        OrderStatus orderStatus = orderStatusRepository.findOrderStatusByProductId(id);
        orderStatus.setOrderStatus(product.getProductStatus());
        orderStatus.setOrderDelay(product.getProductDelay());
        orderStatusRepository.save(orderStatus);
        return product;
    }

    /*
     *  editRequestStatus() : Update the RequestStatus (Approved or Rejected)
     */
    @Override
    public Product editRequestStatus(int id, String reqStatus) throws ProductNotFoundException{
        logger.info("productService: editRequestStatus() method invoke");
        Product item = productRepository.findById(id).orElse(null);
        if(item == null) {
            throw new ProductNotFoundException("Product with ID: " +id+ " not found");
        }
        item.setReqStatus(reqStatus);
        if(reqStatus.equalsIgnoreCase("Reject")) {
            return productRepository.save(item);
        }
        item = productRepository.save(item);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderDelay(item.getProductDelay());
        orderStatus.setOrderStatus(item.getProductStatus());
        orderStatus.setProductEstimatedDeliveryDate(item.getProductEstimatedDeliveryDate());
        orderStatus.setProduct(item);
        orderStatusRepository.save(orderStatus);
        logger.info("productService: editRequestStatus() method end");
        return item;
    }
    /*
     * deleteProduct() : Delete product from the database of given ID
     */

    @Override
    public void deleteProduct(int id) throws ProductNotFoundException{
        logger.info("productService: deleteProduct() method invoke");
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException("Product with ID: " +id+ " not found");
        }
        logger.info("productService: deleteProduct() method end");
        productRepository.deleteById(id);
    }



}