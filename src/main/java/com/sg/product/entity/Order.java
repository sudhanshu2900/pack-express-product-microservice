package com.sg.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private int orderId;
    @Column(name = "Customer_ID")
    private int customerId;
    @Column(name = "Product_Name")
    private String productName;
    @Column(name = "Product_Detail")
    private String productDescription;
    @Column(name = "Origin_Address")
    private String productOrigin;
    @Column(name = "Destination_Address")
    private String productDestination;
    @Column(name = "Order_Service_Rating")
    private int rating=0;

}