package com.springbootslasticsearch;

import com.springbootslasticsearch.pojo.Product;
import com.springbootslasticsearch.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootslasticsearchApplicationTests {


    @Autowired
    ProductRepository productRepository;

    @Test
    public void contextLoads() {

        List<Product> list = new ArrayList<>();
        for (int i=1 ; i<5; i++){
            Product product = new Product();
            product.setId(i+i+i+i+i+i);
            product.setName(i+"李宁"+i);
            product.setColor(i+"红黑色"+i);
            product.setPrice(i+100+i);
            productRepository.index(product);
            list.add(product);
        }

        Iterable<Product> productlist = productRepository.saveAll(list);
        Iterator<Product> it=productlist.iterator();
        int count=0;
        while (it.hasNext()){
            System.out.println(it.next());
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void testSearchAll(){
        for (Product product : productRepository.findAll()) {
            System.out.println(product);
        }
    }

    @Test
    public void testList(){
        List<Integer> list =new ArrayList<>();
        for (Integer i=0 ; i<10 ;i++){
            list.add(i);
        }
        int a=1;
        a+=+1;
    }

    @Test
    public void testSearchName(){
        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(12);
        ids.add(3);

        for (Product product : productRepository.findProductsByIdIn(ids)) {
            System.out.println(product);
        }

        System.out.println("#######################");

        for (Product product : productRepository.findProductsByNameLike("0")) {
            System.out.println(product);
        }

        System.out.println("#######################");

        for (Product product : productRepository.findProductsByPriceIsLessThan(103)) {
            System.out.println(product);
        }

        System.out.println("#######################");

        for (Product product : productRepository.findProductsByColorLike("黑")) {
            System.out.println(product);
        }

        System.out.println("#######################");

        for (Product product : productRepository.findProductsByNameAfterOrderByPriceDesc("李")) {
            System.out.println(product);
        }

        System.out.println("#######################");

        for (Product product : productRepository.findProductsByNameStartingWithAndColorEndingWith("1","1")) {
            System.out.println(product);
        }

        System.out.println("#######################");

        for (Product product : productRepository.findProductsByNameStartingWithOrColorEndingWith("1","2")) {
            System.out.println(product);
        }

    }

}
