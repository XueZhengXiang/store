package com.project.store.mapper;

import com.project.store.entity.Cart;
import com.project.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setPid(2);
        cart.setNum(3);
        cart.setPrice(4L);
        Integer rows = cartMapper.insert(cart);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateNumByCid() {
        Integer integer = cartMapper.updateNumByCid(1, 9, "张三", new Date());
        System.out.println(integer);
    }

    @Test
    public void findByUidAndPid() {
        Cart byUidAndPid = cartMapper.findByUidAndPid(1, 2);
        System.out.println(byUidAndPid);
    }

    @Test
    public void findVOByUid() {
        List<CartVO> voByUid = cartMapper.findVOByUid(16);
        for (CartVO cart: voByUid) {
            System.out.println(cart);
        }
    }
    @Test
    public void findByCid() {
        Cart byCid = cartMapper.findByCid(5);
        System.out.println(byCid);
    }

    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 7, 8, 9, 10};
        List<CartVO> list = cartMapper.findVOByCids(cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

}
