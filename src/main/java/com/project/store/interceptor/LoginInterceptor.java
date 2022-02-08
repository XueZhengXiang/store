package com.project.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**登录拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //HttpServletRequest来获取session对象
        Object uid = request.getSession().getAttribute("uid");
        if (uid == null) {
            response.sendRedirect("/web/login.html");
            return false;
            //return "redirect:/login";-->Thymself模板引擎
        } else
            return true;
    }
}
