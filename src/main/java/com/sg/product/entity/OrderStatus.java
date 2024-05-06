package com.sg.product.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Status ID")
    private int statusId;
    @ManyToOne
    @JoinColumn(name = "p_id")
    private Product product;
    @Column(name="Order Status")
    private String orderStatus;
    @Column(name="Estimate Delivery Date")
    private LocalDate productEstimatedDeliveryDate;
    @Column(name="Order Delay")
    private String orderDelay = "No Delay";

}