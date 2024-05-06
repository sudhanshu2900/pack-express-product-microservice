package com.sg.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sg.product.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}