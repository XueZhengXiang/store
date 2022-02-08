package com.project.store.service;

import com.project.store.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询热销商品的前四名
     *
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();
    List<Product> findNewList();
    /**
     * 根据商品id查询商品详情
     *
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
