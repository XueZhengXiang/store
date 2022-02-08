package com.project.store.mapper;

import com.project.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

//@Mapper-->不建议这么写,因为会有好多,所以在启动类上写StoreApplication

/**
 * 用户模块的接口
 */
public interface UserMapper {

    //注册
    Integer insert(User user);

    //登录
    User findByUsername(String username);

    //修改密码
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    //修改用户信息
    Integer updateInfoByUid(User user);

    User findByUid(Integer uid);

    /**
     * 根据uid更新用户的头像
     *
     * @param uid          用户的id
     * @param avatar       新头像的路径
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}




















