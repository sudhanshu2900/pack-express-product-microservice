package com.sg.product.service;

import java.util.List;

import com.sg.product.entity.Product;
import com.sg.product.exception.ProductNotFoundException;
import com.sg.product.pojo.StatusModel;

public interface ProductService {
    public Product createProduct(Product product);
    public List<Product> getAllRequestedProducts();
    public List<Product> getAllApprovedProducts();
    public List<Product> getAllProductsByCustomerId(int cust_id);
    public Product getProductById(int id) throws ProductNotFoundException;
    public Product editProduct(Product product, int id) throws ProductNotFoundException;
    public Product editRequestStatus(int id, String reqStatus) throws ProductNotFoundException;
    public void deleteProduct(int id) throws ProductNotFoundException;

    public Product editProduct(StatusModel model, int id) throws ProductNotFoundException;

}