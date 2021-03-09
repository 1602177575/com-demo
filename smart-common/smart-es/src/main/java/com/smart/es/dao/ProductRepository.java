package com.smart.es.dao;
import com.smart.es.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 继承内置Reposotory
 *
 */

public interface ProductRepository extends ElasticsearchRepository<Product,String> {


}
