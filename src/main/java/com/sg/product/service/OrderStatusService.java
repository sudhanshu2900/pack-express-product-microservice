package com.sg.product.service;

import java.util.List;

import com.sg.product.entity.OrderStatus;
import com.sg.product.exception.OrderStatusNotFoundException;

public interface OrderStatusService {
    public OrderStatus viewOrderStatus(int id) throws OrderStatusNotFoundException;
    public List<OrderStatus> viewAllOrderStatus();

}