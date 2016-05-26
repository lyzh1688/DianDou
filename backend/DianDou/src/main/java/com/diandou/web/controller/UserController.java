package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.authority.vmodel.AuthModel;
import com.diandou.user.service.IUserService;
import com.diandou.user.vmodel.UserModel;
import com.diandou.video.service.IVideoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/userRegister",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AuthModel userRegister(HttpServletRequest request){
        //String userName = request.getParameter("userName");
        String mobile = request.getParameter("mobile");
        String pswd = request.getParameter("password");

        return this.userService.userRegister(mobile,pswd);

    }

    @Authority
    @RequestMapping(value = "/searchUserListByName",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserModel> searchUserListByName(HttpServletRequest request){

        String userName = request.getParameter("userName");
        String followerId = request.getParameter("followerId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");

        if(userName != null) {
            return this.userService.getUserListByName(pageIdx,pageSize,userName,followerId);
        }

        return new ArrayList<UserModel>();
    }

    @Authority
    @RequestMapping(value = "/getUserList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserModel> getUserList(HttpServletRequest request){

        String roleId = request.getParameter("roleId");
        String followerId = request.getParameter("followerId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");

        if(roleId != null) {
            return this.userService.getUserListByRole(pageIdx,pageSize,roleId,followerId);
        }

        return new ArrayList<UserModel>();
    }
}
