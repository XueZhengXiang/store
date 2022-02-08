package com.project.store.service;

import com.project.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
//RunWith单元测试必须有的
@RunWith(SpringRunner.class)
public class CartServiceTests {
    @Autowired
    private ICartService iCartService;

    @Test
    public void addToCart() {
        iCartService.addToCart(2, 10000014, 6, "赵四");
    }

    @Test
    public void addNum() {
        Integer num = iCartService.addNum(11, 11, "小华腾");
        System.out.println(num);
    }
    @Test
    public void subNum() {
        Integer num = iCartService.subNum(6, 16, "小华腾");
        System.out.println(num);
    }

    @Test
    public void getVOByCids() {
        Integer cids[] = { 4, 5, 6, 7};
        List<CartVO> voByCids = iCartService.getVOByCids(16, cids);
        for (CartVO c : voByCids) {
            System.out.println(c);
        }
    }

}
