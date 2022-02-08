package com.project.store.service.impl;

import com.project.store.entity.Cart;
import com.project.store.entity.Product;
import com.project.store.mapper.CartMapper;
import com.project.store.service.ICartService;
import com.project.store.service.IProductService;
import com.project.store.service.ex.AccessDeniedException;
import com.project.store.service.ex.CartNotFoundException;
import com.project.store.service.ex.InsertException;
import com.project.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service

public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService iProductService;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        // 根据参数pid和uid查询购物车中的数据
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：表示该用户并未将该商品添加到购物车
            // -- 创建Cart对象
            // -- 封装数据：uid,pid,amount
            // -- 调用productService.findById(pid)查询商品数据，得到商品价格
            // -- 封装数据：price
            // -- 封装数据：4个日志
            // -- 调用insert(cart)执行将数据插入到数据表中
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product = iProductService.findById(pid);
            Long price = product.getPrice();
            cart.setPrice(price);
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入商品数据时出现未知错误，请联系系统管理员");
            }
        } else {
            // 否：表示该用户的购物车中已有该商品
            // -- 从查询结果中获取购物车数据的id
            // -- 从查询结果中取出原数量，与参数amount相加，得到新的数量
            // -- 执行更新数量
            Integer cid = result.getCid();
            int num = result.getNum() + amount;
            cartMapper.updateNumByCid(cid, num, username, new Date());

        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        // 是：抛出CartNotFoundException
        if (result == null) {
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }
        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() + 1;
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public Integer subNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        // 是：抛出CartNotFoundException
        if (result == null) {
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }
        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() - 1;
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
//        for (CartVO c : list) {
//            if (!c.getUid().equals(uid)) {
//                list.remove(c);
//            }
//        }
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }
}














