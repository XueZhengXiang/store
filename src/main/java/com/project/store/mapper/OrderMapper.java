package com.project.store.mapper;

import com.project.store.entity.Order;
import com.project.store.entity.OrderItem;

public interface OrderMapper {
    /**
     * 插入订单数据
     *
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     *
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
