package com.sg.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sg.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("select p from Product p where p.reqStatus='Request'")
    public List<Product> findAllReqProducts();
    @Query("select p from Product p where p.reqStatus='Approve'")
    public List<Product> findAllApprovedProducts();
    @Query("select p from Product p where p.order.customerId=?1")
    public List<Product> findAllProductsAssociatedWithCustomerId(int customer_id);
    /*
     * These queries are used in excel code to export the records
     */
    @Query("select p from Product p where p.reqStatus='Reject'")
    public List<Product> findAllRejectedProducts();
    @Query("select p from Product p where p.productStatus='In Transit'")
    public List<Product> findAllInTransitProducts();
    @Query("select p from Product p where p.productStatus='Delivered'")
    public List<Product> findAllDeliveredProducts();
}