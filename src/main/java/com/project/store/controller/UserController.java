package com.project.store.controller;

import com.project.store.controller.ex.*;
import com.project.store.entity.User;
import com.project.store.service.IUserService;
import com.project.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
    public JsonResult<Void> req(User user) {
//        JsonResult<Void> jsonResult = new JsonResult<>();
//        try {
//            userService.reg(user);
//            jsonResult.setState(200);
//            jsonResult.setMessage("注册成功");
//
//        } catch (UserNameDuplicatedException e) {
//            jsonResult.setState(4000);
//            jsonResult.setMessage("用户名已占用");
//        } catch (InsertException e) {
//            jsonResult.setState(5999);
//            jsonResult.setMessage("注册异常");
//        }
//        return jsonResult;
        userService.reg(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User userData = userService.login(username, password);
        Integer uid = userData.getUid();
        String username1 = userData.getUsername();
        session.setAttribute("uid", uid);
        session.setAttribute("username", username1);
        //获取session数据-->8张三
     System.out.println(getUIdFromSession(session) + getUsernameFromSession(session));
        return new JsonResult<User>(OK, userData);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> change(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<Void>(OK);
    }

    /**
     * 传送这种格式多状态-->state
     * {"state":200,"data":
     * {"createdUser":null,"createdTime":null,"modifiedUser":null,
     * "modifiedTime":null,"uid":null,"username":null,"password":null,
     * "salt":null,"phone":null,"email":null,"gender":null,"avatar":null,
     * "isDelete":null},"message":null}
     * <p>
     * {"createdUser":null,"createdTime":null,"modifiedUser":null,
     * "modifiedTime":null,"uid":null,"username":null,"password":null,
     * "salt":null,"phone":null,"email":null,"gender":null,"avatar":null,"isDelete":null}
     */
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUId(HttpSession session) {
        Integer uId = getUIdFromSession(session);
        User data = userService.getUId(uId);
        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<Void>(OK);
    }

    /**
     * 头像文件大小的上限值(10MB)
     */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 允许上传的头像的文件类型
     */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @RequestMapping("/change_avatar")
    //第一种、file-->	<input type="file" name="file">
    //第二种、或者@RequestParma("file")修饰MultipartFile multipartFile
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {
        // 判断上传的文件是否为空
        // 是：抛出异常
        if (file.isEmpty()) {
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        // 判断上传的文件大小是否超出限制值
        // 是：抛出异常
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }
        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();//创建当前目录
        }
        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        //UUID将来生成一个新的字符串作为文件名
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }
        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userService.changeAvatar(uid, username, avatar);

        // 返回成功头像路径
        //{"state":200,"data":"/upload/ecca6f43-73b7-4724-bb92-c601bee1c910.jpg","message":null}
        return new JsonResult<String>(OK, avatar);
    }
}












