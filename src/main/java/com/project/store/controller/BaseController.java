package com.project.store.controller;

import com.project.store.controller.ex.*;
import com.project.store.service.ex.*;
import com.project.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 异常处理的基类
 */
public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class})//统一处理异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> jsonResult = new JsonResult<>(e);
        if (e instanceof UserNameDuplicatedException) {
            jsonResult.setState(4000);
            jsonResult.setMessage("用户名已占用");
        } else if (e instanceof InsertException) {
            jsonResult.setState(4001);
            jsonResult.setMessage("注册异常");
        } else if (e instanceof UserNotFoundException) {
            jsonResult.setState(5000);
            jsonResult.setMessage("用户数据不存在");
        } else if (e instanceof AddressNotFoundException) {
            jsonResult.setState(4004);
            jsonResult.setMessage("用户收货地址不存在");
        } else if (e instanceof ProductNotFoundException) {
            jsonResult.setState(4006);
            jsonResult.setMessage("尝试访问的商品数据不存在");
        } else if (e instanceof AccessDeniedException) {
            jsonResult.setState(4005);
            jsonResult.setMessage("收货地址非法数据");
        } else if (e instanceof CartNotFoundException) {
            jsonResult.setState(4007);
            jsonResult.setMessage("尝试访问的购物车数据不存在");
        } else if (e instanceof PasswordNotMatchException) {
            jsonResult.setState(5001);
            jsonResult.setMessage("用户名密码错误");
        } else if (e instanceof DeleteException) {
            jsonResult.setState(5002);
            jsonResult.setMessage("用户删除地址错误");
        } else if (e instanceof UpdateException) {
            jsonResult.setState(5555);
            jsonResult.setMessage("用户密码更新错误");
        }//-->新加入FileUploadException.class
        else if (e instanceof FileEmptyException) {
            jsonResult.setState(6000);
            jsonResult.setMessage("上传文件为空");
        } else if (e instanceof FileSizeException) {
            jsonResult.setState(6001);
            jsonResult.setMessage("上传文件大小出错");
        } else if (e instanceof FileTypeException) {
            jsonResult.setState(6002);
            jsonResult.setMessage("上传文件类型出错");
        } else if (e instanceof FileStateException) {
            jsonResult.setState(6003);
            jsonResult.setMessage("上传文件状态出错");
        } else if (e instanceof FileUploadIOException) {
            jsonResult.setState(6004);
            jsonResult.setMessage("上传文件读写异常");
        } else if (e instanceof AddressCountLimitException) {
            jsonResult.setState(4003);
            jsonResult.setMessage("收货地址已经达到上限值");
        }
        return jsonResult;
    }

    protected final Integer getUIdFromSession(HttpSession session) {
        String uid = session.getAttribute("uid").toString();
        return Integer.valueOf(uid);
    }

    protected final String getUsernameFromSession(HttpSession session) {
        String username = session.getAttribute("username").toString();
        return username;
    }
}















