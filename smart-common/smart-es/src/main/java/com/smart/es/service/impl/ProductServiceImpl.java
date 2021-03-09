package com.smart.es.service.impl;

import com.smart.es.dao.ProductRepository;
import com.smart.es.entity.Product;
import com.smart.es.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductRepository productRepository;


    @Override
    public boolean add(Product product) {
        Product save = productRepository.save(product);
        System.out.println(save);
        return false;
    }

    @Override
    public Page<Product> getProductsByPage(String key, int page, int size) {
        //PageRequest
        Page<Product> all = productRepository.findAll(PageRequest.of(page, size));
        return all;
    }
}
