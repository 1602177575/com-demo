package com.smart.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.smart.store.entity.Storage;
import com.smart.store.mapper.StorageMapper;
import com.smart.store.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@Service
public class StorageServiceImpl  implements StorageService {
    @Resource
    StorageMapper storageMapper;
    /**
     *
     * @param productId
     * @param quantity
     */
    @Override
    public void decrease(int productId, int quantity) throws Exception {
        Storage storage = storageMapper.selectOne(new QueryWrapper<Storage>().eq(Storage.COL_PRODUCT_ID,productId));
        if (storage.getResidue() >= quantity){
            quantity = storage.getResidue() - quantity;
            storage.setResidue(quantity);
            storageMapper.updateById(storage);
            Storage storage2 = storageMapper.selectOne(new QueryWrapper<Storage>().eq(Storage.COL_PRODUCT_ID,productId));
            System.out.println("当前库存"+storage2);
        }else {
            throw  new  Exception("库存不够!!!!!,减库存失败");
        }
    }
}
