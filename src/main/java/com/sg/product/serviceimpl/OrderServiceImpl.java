package com.sg.product.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.product.entity.Order;
import com.sg.product.entity.Product;
import com.sg.product.exception.OrderNotFoundException;
import com.sg.product.repository.OrderRepository;
import com.sg.product.service.OrderService;
import com.sg.product.service.ProductService;

/*
 * Order Service class that handles the business logic.
 * This class implements OrderService Interface
 */

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    /*
     * createOrder() : It create a new order and simultaneously data is insert into Product table
     */

    @Override
    public Order createOrder(Order order) {
        logger.info("orderService: createOrder() method invoke");
        Order newOrder = orderRepository.save(order);
        Product product = new Product();
        product.setOrder(newOrder);
        product.setProductName(newOrder.getProductName());
        product.setProductDescription(newOrder.getProductDescription());
        product.setProductOrigin(newOrder.getProductOrigin());
        product.setProductDestination(newOrder.getProductDestination());
        productService.createProduct(product);
        logger.info("orderService: createOrder() method end");
        return newOrder;
    }
    /*
     * getAllOrders() : Get all the existing orders
     */

    @Override
    public List<Order> getAllOrders() {
        logger.info("orderService: getAllOrders() method invoke");
        List<Order> orderList = orderRepository.findAll();
        logger.info("orderService: getAllOrders() method end");
        return orderList;
    }
    /*
     * getOrderById() : Get a order that is associated with its order_id
     */

    @Override
    public Order getOrderById(int id) throws OrderNotFoundException {
        logger.info("orderService: getOrderById() method invoke");
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null) {
            throw new OrderNotFoundException("Order with ID: " +id+ " not found");
        }
        logger.info("orderService: getOrderById() method end");
        return order;
    }
    /*
     * editOrder() : Update the information of a order with associated order_id
     */

    @Override
    public Order editOrder(Order order, int id) throws OrderNotFoundException {
        logger.info("orderService: editOrder() method invoke");
        Order newOrder = orderRepository.findById(id).orElse(null);
        if(newOrder == null) {
            throw new OrderNotFoundException("Order with ID: " +id+ " not found");
        }
        newOrder.setCustomerId(order.getCustomerId());
        newOrder.setProductName(order.getProductName());
        newOrder.setProductDescription(order.getProductDescription());
        newOrder.setProductOrigin(order.getProductOrigin());
        newOrder.setProductDestination(order.getProductDestination());
        newOrder.setRating(order.getRating());
        logger.info("orderService: editOrder() method end");
        return orderRepository.save(newOrder);
    }

}