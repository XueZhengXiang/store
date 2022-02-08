package com.project.store.service.impl;

import com.project.store.entity.User;
import com.project.store.mapper.UserMapper;
import com.project.store.service.IUserService;
import com.project.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    //注册
    @Override
    public void reg(User user) {
        //调用findByUsername是否被注册过
        User result = userMapper.findByUsername(user.getUsername());
        if (result == null) {  //没有就注册
            //密码优化-->盐值+password+盐值
            String salt = UUID.randomUUID().toString().toUpperCase();
            user.setSalt(salt);
            String md5password = MD5(salt, user.getPassword());
            user.setPassword(md5password);
            //补全数据
            user.setIsDelete(0);
            user.setCreatedUser(user.getUsername());
            user.setModifiedUser(user.getUsername());
            Date date = new Date();
            user.setCreatedTime(date);
            user.setModifiedTime(date);
            Integer rows = userMapper.insert(user);
            if (rows != 1) {
                throw new InsertException("在用户注册时产生异常");
            }
        } else {
            throw new UserNameDuplicatedException("该用户名已存在,请重新注册");
        }
    }

    private String MD5(String salt, String password) {
        //调用方法加密三次,以字节输入
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    //登录
    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //1、现获取数据库加密的密码
        String DBPsd = result.getPassword();
        //2、和现在的密码对比
        String salt = result.getSalt();
        String pwd = MD5(salt, password);
        //3、密码对比
        if (!pwd.equals(DBPsd)) {
            throw new PasswordNotMatchException("用户名密码错误");
        }
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户已删除,用户数据不存在");
        }
        //User user = userMapper.findByUsername(username);
        //返回user对象辅助其他页面开发
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    //修改密码
    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码和数据库密码作比较
        /**这里重新设置一个salt为什么不对！！！*/
        String salt = result.getSalt();
        String oldMd5Password = MD5(salt, oldPassword);
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        //将新的密码设置到数据库
        String newMd5Password = MD5(salt, newPassword);
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生异常");
        }
    }

    //辅助的得到user数据
    @Override
    public User getUId(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        //后端传送给前端数据只要这些,不给的为null
        User user = new User();
        user.setPhone(result.getPhone());
        user.setGender(result.getGender());
        user.setUsername(result.getUsername());
        user.setEmail(result.getEmail());
        return user;
    }

    //从getUID-->修改信息
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        //  user.setUsername(username);
        user.setUid(uid);
        user.setModifiedUser(username);//修改者
        Date date = new Date();
        user.setModifiedTime(date);
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据出现异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新用户数据产生异常");
        }
    }
}
















