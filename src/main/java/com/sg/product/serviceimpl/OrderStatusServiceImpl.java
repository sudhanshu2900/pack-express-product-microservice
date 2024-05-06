package com.sg.product.serviceimpl;

/*
 * OrderStatus Service class the handle the business logic of excel part
 * This class implements OrderStatusService Interface
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.product.entity.OrderStatus;
import com.sg.product.exception.OrderStatusNotFoundException;
import com.sg.product.repository.OrderStatusRepository;
import com.sg.product.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{

    @Autowired
    OrderStatusRepository orderStatusRepository;
    Logger logger = LoggerFactory.getLogger(OrderStatusServiceImpl.class);
    /*
     * viewOrderStatus() : Get status of a order that associated with given status_Id
     */
    @Override
    public OrderStatus viewOrderStatus(int id) throws OrderStatusNotFoundException{
        logger.info("OrderStatusServiceImpl: viewOrderStatus() method invoke");
        OrderStatus status = orderStatusRepository.findById(id).orElse(null);
        if(status == null) {
            throw new OrderStatusNotFoundException("Order with ID: " + id + " is not found");
        }
        logger.info("OrderStatusServiceImpl: viewOrderStatus() method end");
        return orderStatusRepository.findOrderStatusByOrderId(id);
    }
    /*
     * viewAllOrderStatus() : Get status of all the existing orders
     */

    @Override
    public List<OrderStatus> viewAllOrderStatus() {
        logger.info("OrderStatusServiceImpl: viewAllOrderStatus() method invoke");
        List<OrderStatus> orderStatusList =  orderStatusRepository.findAll();
        logger.info("OrderStatusServiceImpl: viewAllOrderStatus() method end");
        return orderStatusList;
    }

}