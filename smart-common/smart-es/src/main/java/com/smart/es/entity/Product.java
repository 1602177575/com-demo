package com.smart.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 仅仅存放查询
 */
@Data
@Document(indexName = "products")
public class Product implements Serializable {
    @Id
    private int productId;
    @Field
    private String title;
    private String price; //价格
    private String content; //库存

}
