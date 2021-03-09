package com.smart.db.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.db.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


public interface ProductMapper extends BaseMapper<Product> {
}