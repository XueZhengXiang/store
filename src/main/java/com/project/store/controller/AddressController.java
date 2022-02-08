package com.project.store.controller;

import com.project.store.entity.Address;
import com.project.store.service.IAddressService;
import com.project.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService iAddressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象的方法执行业务
        iAddressService.addNewAddress(uid, username, address);
        // 响应成功
        return new JsonResult<Void>(OK);
    }

    /**
     * {"state":200,"data":[{"createdUser":null,"createdTime":null,"modifiedUser":null,
     * "modifiedTime":null,"aid":12,"uid":null,"name":null,"provinceName":null,"provinceCode":null,
     * "cityName":null,"cityCode":null,"areaName":null,"areaCode":null,"zip":null,"address":null,
     * "phone":null,"tel":null,"tag":null,"isDefault":0},{"createdUser":null,"createdTime":null,
     * "modifiedUser":null,"modifiedTime":null,"aid":11,"uid":null,"name":"张飞","provinceName":"江苏省",
     * "provinceCode":null,"cityName":"无锡市","cityCode":null,"areaName":"宜兴市","areaCode":null,
     * "zip":"123456","address":"公司","phone":"18326042614","tel":"10086","tag":"学校","isDefault":0},
     */
    @RequestMapping({"", " /"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUIdFromSession(session);
        List<Address> data = iAddressService.getByUid(uid);
        // 响应成功
        return new JsonResult<List<Address>>(OK, data);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        iAddressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        iAddressService.delete(aid, uid, username);
        return new JsonResult<Void>(OK);
    }
}













