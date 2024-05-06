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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.product.entity.OrderStatus;
import com.sg.product.exception.OrderStatusNotFoundException;
import com.sg.product.service.OrderStatusService;

/*
 * OrderStatusController is a RestController that handle the API request coming from client and invoke appropriate business method
 * author: Sudhanshu Gupta
 */

@RestController
@RequestMapping("/api/v1/status")
@CrossOrigin(value = "http://localhost:3000/")
public class OrderStatusController {
    @Autowired
    OrderStatusService orderStatusService;
    Logger logger = LoggerFactory.getLogger(OrderStatusController.class);
    /*
     * getOrderStatusByOrderId(): Display order status data by providing corresponding order_id
     */
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderStatus> getOrderStatusByOrderId(
            @PathVariable int order_id) throws OrderStatusNotFoundException{
        logger.info("orderStatusController: getOrderStatusByOrderId() method invoke");
        OrderStatus status = orderStatusService.viewOrderStatus(order_id);
        logger.info("orderStatusController: getOrderStatusByOrderId() method end");
        return new ResponseEntity<OrderStatus>(status, HttpStatus.OK);
    }
    /*
     * getAllOrderStatus(): Display all existing order status in the system
     */
    @GetMapping("/allorders")
    public ResponseEntity<List<OrderStatus>> getAllOrderStatus(){
        logger.info("orderStatusController: getAllOrderStatus() method invoke");
        List<OrderStatus> allStatus = orderStatusService.viewAllOrderStatus();
        logger.info("orderStatusController: getAllOrderStatus() method end");
        return new ResponseEntity<List<OrderStatus>>(allStatus, HttpStatus.OK);
    }

}