package com.project.store.service;

import com.project.store.entity.Address;


import java.util.List;

public interface IAddressService {
    /**
     * 创建新的收货地址
     *
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @param address  用户提交的收货地址数据
     *                 ---->不需要把俩个抽象方法都写上insert和countByUid
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * @param uid 当前登录的用户的id
     * @return 返回当前登录的订单
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置默认收货地址
     *
     * @param aid      收货地址id
     * @param uid      归属的用户id
     * @param username 当前登录的用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除收货地址
     *
     * @param aid      收货地址id
     * @param uid      归属的用户id
     * @param username 当前登录的用户名
     */
    void delete(Integer aid, Integer uid, String username);

    /**
     * -->辅助IOrderService
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);

}
