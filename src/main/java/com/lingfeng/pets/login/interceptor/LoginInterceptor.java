package com.lingfeng.pets.login.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.login.JsonUtils;
import com.lingfeng.pets.login.NoneAuth;
import com.lingfeng.pets.login.TokenHelper;


@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private TokenHelper tokenHelper;
    
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        //如果被@NoneAuth注解代表不需要登录验证，直接通过
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.getAnnotation(NoneAuth.class) != null) return true;       
        
        if (tokenHelper.verifyToke(token))  return true;    
        //验证未通过
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JsonUtils.obj2String(ServerResponse.createByErrorCodeMessage(401, "认证失败！")));
        return false;
    }

}
