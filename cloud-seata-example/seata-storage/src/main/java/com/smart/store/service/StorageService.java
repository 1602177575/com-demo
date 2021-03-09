package com.smart.store.service;

public interface StorageService {
    /**
     * 减少库存
     *
     * @param productId
     * @param quantity
     */
    void decrease(int productId, int quantity) throws Exception;
}
