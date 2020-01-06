package com.springbootslasticsearch.repository;

import com.springbootslasticsearch.pojo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ProductRepository extends ElasticsearchRepository<Product,Integer> {

    List<Product> findProductsByNameLike(String name);

    List<Product> findProductsByPriceIsLessThan(float price);

    List<Product> findProductsByColorLike(String color);

    List<Product> findProductsByNameAfterOrderByPriceDesc(String name);

    List<Product> findProductsByNameStartingWithAndColorEndingWith(String name,String color);

    List<Product> findProductsByNameStartingWithOrColorEndingWith(String name,String color);

    List<Product> findProductsByIdIn(List<Integer> ids);
}
