package com.project.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class District implements Serializable {
    private Integer id;//标识
    private String parent;//上一级编号,省上一级+86
    private String code;//本身编号
    private String name;//自己名字
}
