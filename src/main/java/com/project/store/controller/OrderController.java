package com.project.store.controller;

import com.project.store.entity.Order;
import com.project.store.service.IOrderService;
import com.project.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    /**
     * {"state":200,"data":{"createdUser":null,"createdTime":null,"modifiedUser":null,
     * "modifiedTime":null,"oid":5,"uid":16,"recvName":"张飞","recvPhone":"18326042614",
     * "recvProvince":"吉林省","recvCity":"长春市","recvArea":"南关区","recvAddress":"斌峰时代城123",
     * "totalPrice":null,"status":null,"orderTime":null,"payTime":null},"message":null}
     *
     * @param aid 地址编号
     * @param cids 购物车编号
     * @param session
     * @return
     */
    @RequestMapping("/create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid, username);
        // 返回成功与数据
        return new JsonResult<Order>(OK, data);
    }
}