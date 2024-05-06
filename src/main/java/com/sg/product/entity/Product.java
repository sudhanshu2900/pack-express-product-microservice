package com.sg.product.entity;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
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
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int productId;

    @Column(name = "Name")
    private String productName;

    @Column(name = "Description")
    private String productDescription;

    @Column(name = "Origin")
    private String productOrigin;

    @Column(name = "Destination")
    private String productDestination;

    @Column(name = "Status")
    private String productStatus = "Order Received";

    @Column(name = "Delivery Date")
    private LocalDate productEstimatedDeliveryDate = LocalDate.now().plusDays(5);

    @Column(name="Delay")
    private String productDelay = "No Delay";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="o_id")
    private Order order;

    @Column(name="Request Status")
    private String reqStatus = "Request";

}
