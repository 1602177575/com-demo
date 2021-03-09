package com.smart.es.controller;

import com.smart.es.entity.Product;
import com.smart.es.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    ProductService productService;

    @PostMapping("/")
    public String test(){
        Page<Product> page = productService.getProductsByPage("", 1, 1);
        return page.toString();
    }
}
