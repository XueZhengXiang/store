package com.project.store.service;

import com.project.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private IOrderService iOrderService;
    @Test
    public void create(){
        Integer cids[]={2,3,4,5,6};
        Order order = iOrderService.create(20, cids, 16, "孙悟空");
        System.out.println(order);
    }
}
