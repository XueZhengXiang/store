package com.project.store.service;

import com.project.store.entity.Product;
import com.project.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @Autowired
    private IProductService iProductService;

    @Test
    public void findHostList() {
        List<Product> hotList = iProductService.findHotList();
        for (Product product : hotList) {
            System.out.println(product.getModifiedTime());
            ;
            System.out.println(product);
        }
    }

    @Test
    public void findById() {
        try {
            Integer id = 10000017;
            Product result = iProductService.findById(id);
            System.out.println(result);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
