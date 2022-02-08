package com.project.store.mapper;

import com.project.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> hotList = productMapper.findHotList();
        for (Product p : hotList) {
            System.out.println(p);
        }
    }
    @Test
    public void findNewList() {
        List<Product> hotList = productMapper.findNewList();
        for (Product p : hotList) {
            System.out.println(p);
        }
    }

    @Test
    public void finsById(){
        Product byId = productMapper.findById(10000012);
        System.out.println(byId);
        //System.out.println(byId.toString());
    }
}
