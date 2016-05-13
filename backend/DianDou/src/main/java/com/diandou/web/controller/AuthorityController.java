package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.authority.service.IAuthorityService;
import com.diandou.authority.vmodel.AuthModel;
import com.diandou.common.Authority.TokenContainer;
import com.diandou.enumerable.AuthStatusEnum;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 胡志洁 on 2016/5/8.
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private IAuthorityService authorityService;

    @Authority
    @RequestMapping(value = "/logout",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String logout(String token){
        TokenContainer.GetInstance().getTokenUserCache().remove(token);
        return  new Gson().toJson(new AuthModel.Builder().authStatus(AuthStatusEnum.logout).build());
    }

    @RequestMapping(value = "/loginAuthority",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginAuthority(HttpServletRequest request){

        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        AuthModel authModel = this.authorityService.loginAuthority(mobile,password);

        if(!TokenContainer.GetInstance().getTokenUserCache().containsKey(authModel.getSessionToken())) {
            TokenContainer.GetInstance().getTokenUserCache().put(authModel.getSessionToken(), authModel.getUserId());
        }
        return  new Gson().toJson(authModel);
    }

    @RequestMapping(value = "/loginAuthorityFailed",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginAuthorityFailed(){
        return  new Gson().toJson(this.authorityService.loginAuthorityFailed());
    }

}
