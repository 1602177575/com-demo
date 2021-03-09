package com.smart.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author mtl
 */
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.smart.es.dao")
public class EsDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsDemoApplication.class,args);
    }
}
