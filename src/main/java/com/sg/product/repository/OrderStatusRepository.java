package com.sg.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sg.product.entity.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{
    @Query("select o from OrderStatus o where o.product.order.orderId =?1")
    public OrderStatus findOrderStatusByOrderId(int order_id);
    @Query("select o from OrderStatus o where o.product.productId =?1")
    public OrderStatus findOrderStatusByProductId(int product_id);

}