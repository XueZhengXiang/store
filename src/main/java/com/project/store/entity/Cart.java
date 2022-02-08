package com.project.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/** 购物车数据的实体类 */
@Data
@ToString
public class Cart extends BaseEntity implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;

    // Generate: Getter and Setter、Generate hashCode() and equals()、toString()
}
