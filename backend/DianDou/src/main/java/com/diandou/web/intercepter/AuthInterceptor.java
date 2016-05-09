package com.diandou.web.intercepter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.diandou.annotation.Authority;
import com.diandou.common.Authority.TokenContainer;
import com.diandou.enumerable.AuthFieldEnum;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/8.
 */

public class AuthInterceptor extends HandlerInterceptorAdapter {

    //private final String TOKEN = "DIANDOU_TOKEN";

    public boolean validate(HttpServletRequest request){
        String token = request.getHeader("X-Auth-Token");
        if (null == token) {
            return false;
        }

        if(TokenContainer.GetInstance().getTokenUserCache().containsKey(token)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            Authority authority = ((HandlerMethod) handler).getMethodAnnotation(Authority.class);

            //没有声明需要权限,或者声明不验证权限
            if(authority == null || authority.validate() == false){
                return true;
            }
            else{
                //在这里实现自己的权限验证逻辑
                if(this.validate(request))//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    return true;
                else//如果验证失败
                {
                    //返回到登录界面
                    response.sendRedirect("/diandou/authority/loginAuthorityFailed");
                    return false;
                }
            }
        }
        else
            return true;
    }
}