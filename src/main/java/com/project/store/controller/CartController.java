package com.project.store.controller;

import com.project.store.service.ICartService;
import com.project.store.util.JsonResult;
import com.project.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    /**
     * http://localhost:8080/carts/add_to_cart?pid=10000017&amount=3
     * {"state":200,"data":null,"message":null}
     *
     * @param pid     用户编号
     * @param amount  增加数量
     * @param session
     * @return
     */
    @RequestMapping("/add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    /**
     * {"state":200,"data":[{"cid":5,"uid":16,"pid":10000022,"price":5119,"num":3,
     * "title":"联想（Lenovo）IdeaPad310经典版黑色","realPrice":5119,"image":"/images/portal/13LenovoIdeaPad310_black/"},
     * {"cid":4,"uid":16,"pid":10000042,"price":4399,"num":11,"title":"联想ThinkPad New S1（01CD） i5 6代 红色","realPrice":4399,"image":"/images/portal/21ThinkPad_New_S1/"},{"cid":3,"uid":16,"pid":10000017,"price":4604,"num":6,
     * "title":"戴尔(DELL)XPS13-9360-R1609 13.3高配版银色","realPrice":4604,"image":"/images/portal/12DELLXPS13-silvery/"}],"message":null}
     *
     * @param session
     * @return
     */
    @GetMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUIdFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
        // 返回成功与数据
        return new JsonResult<List<CartVO>>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(OK, data);
    }
    @RequestMapping("{cid}/num/sub")
    public JsonResult<Integer> subNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUIdFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.subNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(OK, data);
    }

    /**
     * {"state":200,"data":[{"cid":3,"uid":16,"pid":10000017,"price":4604,"num":10,
     * "title":"戴尔(DELL)XPS13-9360-R1609 13.3高配版银色","realPrice":4604,
     * "image":"/images/portal/12DELLXPS13-silvery/"}],"message":null}
     * @param cids 所有商品的编号
     * @param session Session中数据
     * @return
     */
    @GetMapping("/list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUIdFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return new JsonResult<>(OK, data);
    }
}