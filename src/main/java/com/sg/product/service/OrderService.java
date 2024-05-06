package com.sg.product.service;

import java.util.List;

import com.sg.product.entity.Order;
import com.sg.product.exception.OrderNotFoundException;

public interface OrderService {
    public Order createOrder(Order order);
    public List<Order> getAllOrders();
    public Order getOrderById(int id) throws OrderNotFoundException;

    public Order editOrder(Order order, int id) throws OrderNotFoundException;
}