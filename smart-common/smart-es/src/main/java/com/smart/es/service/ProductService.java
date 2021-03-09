package com.smart.es.service;


import com.smart.es.entity.Product;
import org.springframework.data.domain.Page;


public interface ProductService {
    boolean add(Product product);

    Page<Product> getProductsByPage(String key,int size,int page);

}
