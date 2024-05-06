package com.sg.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.product.entity.Order;
import com.sg.product.exception.OrderNotFoundException;
import com.sg.product.service.OrderService;

/*
 * OrderController is a RestController that handle the API request coming from client and invoke appropriate business method
 * author: Sudhanshu Gupta
 */

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(value = "http://localhost:3000/")
public class OrderController {
    @Autowired
    OrderService orderService;
    Logger logger = LoggerFactory.getLogger(OrderController.class);
    /*
     * addOrder() : A new can added through this method
     */
    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        logger.info("orderController: addOrder() method invoke");
        Order item = orderService.createOrder(order);
        logger.info("orderController: addOrder() method end");
        return new ResponseEntity<Order>(item, HttpStatus.OK);
    }
    /*
     * viewAllOrders(): Get all the existing orders from the database
     */
    @GetMapping("/allorders")
    public ResponseEntity<List<Order>> viewAllOrders(){
        logger.info("orderController: viewAllOrders() method invoke");
        List<Order> orderList = orderService.getAllOrders();
        logger.info("orderController: viewAllOrders() method end");
        return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
    }
    /*
     * viewOrderById(): Get the order by its order_id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> viewOrderById(@PathVariable int id) throws OrderNotFoundException{
        logger.info("orderController: viewOrderById() method invoke");
        Order order = orderService.getOrderById(id);
        logger.info("orderController: viewOrderById() method end");
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    /*
     * updateOrder() : Edit the information of Order by its order_id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable int id) throws OrderNotFoundException{
        logger.info("orderController: updateOrder() method invoke");
        Order item = orderService.editOrder(order, id);
        logger.info("orderController: updateOrder() method end");
        return new ResponseEntity<Order>(item, HttpStatus.OK);
    }

}