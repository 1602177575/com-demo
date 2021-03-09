package com.smart.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.store.entity.Storage;import org.apache.ibatis.annotations.Param;

public interface StorageMapper extends BaseMapper<Storage> {
    int updateQuantity(@Param("productId") int productId, @Param("total") int total);
}