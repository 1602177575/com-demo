package com.smart.store.controller;

import com.smart.store.mapper.StorageMapper;
import com.smart.store.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author mtl
 */
@RestController
public class StoreController {
    @Resource
    StorageService storageService;

    @PutMapping("/decr")
    public void decrease(int productId, int quantity) throws Exception {
        storageService.decrease(productId, quantity);
    }
}
