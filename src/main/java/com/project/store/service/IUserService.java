package com.project.store.service;

import com.project.store.entity.User;

/**
 * 用户模块接口
 */
public interface IUserService {
     //注册
     void reg(User user);
     //登录
     User login(String username,String password);
     //修改密码
     void changePassword(Integer uid,
                         String username,
                         String oldPassword,
                         String newPassword);
     //根据UID返回用户的数据
     User getUId(Integer uid);
     //更改信息
     void changeInfo(Integer uid, String username, User user);

     /**
      *
      * @param uid 用户id
      * @param username 用户名称
      * @param avatar 用户头像路径
      */
     void changeAvatar(Integer uid, String username, String avatar);








}
