package com.project.store.mapper;

import com.project.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
//RunWith单元测试必须有的
@RunWith(SpringRunner.class)
public class UserMapperTest {
    //idea自动检测userMapper, 从Inspections->SpringCore->autowired for spring been class改为warring
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("12345");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void login() {
        User byUsername = userMapper.findByUsername("张三");
        System.out.println(byUsername);
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(4, "11111", "管理员", new Date());
    }

    @Test
    public void findByUid() {
        User byUid = userMapper.findByUid(4);
        System.out.println(byUid);
    }

    @Test
    public void updateInfoByUid() {
      User user=new User();
        user.setPhone("10086");
        user.setUid(12);
        user.setEmail("12345@qq.com");
        user.setGender(0);
        user.setModifiedUser("管理员");
        Date date=new Date();
        user.setModifiedTime(date);
        userMapper.updateInfoByUid(user);
        System.out.println(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(12,"/up/avatar.png","超级管理员",new Date());
    }

}
