package com.project.store.service;

import com.project.store.entity.User;
import com.project.store.service.ex.ServiceException;
import com.project.store.service.ex.UpdateException;
import com.project.store.service.ex.UserNotFoundException;
import com.project.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
//RunWith单元测试必须有的
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("张三");
            user.setPassword("11111");
            userService.reg(user);
        } catch (ServiceException e) {
            System.out.println("找异常！！！！！！！！！！！！");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        User user = userService.login("张三", "11111");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        userService.changePassword(16, "张三", "11111", "12345");
        System.out.println("修改密码成功！");
    }

    @Test
    public void getUId() {
        //前端数据只要这些
        User uId = userService.getUId(12);
        System.out.println(uId);
    }

    //从getUID-->修改信息
    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("999");
        user.setGender(0);
        user.setModifiedUser("八戒");
        user.setEmail("12345@qq.com");
        Date date = new Date();
        user.setModifiedTime(date);
        userService.changeInfo(12, "A", user);
    }

    @Test
    public void changeAvatar() {
        userService.changeAvatar(12, "A", "/source/avatar.ong");
    }
}











