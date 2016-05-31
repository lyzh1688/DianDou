package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.authority.vmodel.AuthModel;
import com.diandou.common.util.StringUtil;
import com.diandou.user.entity.User;
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
    @RequestMapping(value = "/getUserInfoById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getUserInfoById(HttpServletRequest request){
        String userId = request.getParameter("userId");
        if(StringUtil.isNullOrEmpty(userId)){
            return null;
        }
        else {
            return this.userService.getUserInfoById(userId);
        }
    }

    @Authority
    @RequestMapping(value = "/searchUserListByName",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchUserListByName(HttpServletRequest request){

        String userName = request.getParameter("userName");
        String followerId = request.getParameter("followerId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");

        if(userName != null) {

            //it's strange that isFollowed attribute becomes to followed when returning ArrayList
            //return this.userService.getUserListByName(pageIdx,pageSize,userName,followerId);
            return new Gson().toJson(this.userService.getUserListByName(pageIdx,pageSize,userName,followerId));

        }

        return new Gson().toJson( new ArrayList<UserModel>());
    }

    @Authority
    @RequestMapping(value = "/getUserList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserList(HttpServletRequest request){

        String roleId = request.getParameter("roleId");
        String followerId = request.getParameter("followerId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");

        if(roleId != null) {

            //it's strange that isFollowed attribute becomes to followed when returning ArrayList
            return new Gson().toJson(this.userService.getUserListByRole(pageIdx,pageSize,roleId,followerId));
        }

        return new Gson().toJson( new ArrayList<UserModel>());
    }

    @Authority
    @RequestMapping(value = "/updateUserName",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean updateUserName(HttpServletRequest request){

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");

        if(StringUtil.isNullOrEmpty(userId)) {
            return false;
        }

        return this.userService.updateUserName(userName,userId);

    }

    @Authority
    @RequestMapping(value = "/updateUserSex",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean updateUserSex(HttpServletRequest request){

        String userId = request.getParameter("userId");
        String sex = request.getParameter("sex");

        if(StringUtil.isNullOrEmpty(userId)) {
            return false;
        }

        return this.userService.updateUserSex(sex,userId);

    }

    @Authority
    @RequestMapping(value = "/updateUserBrief",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean updateUserBrief(HttpServletRequest request){

        String userId = request.getParameter("userId");
        String brief = request.getParameter("brief");

        if(StringUtil.isNullOrEmpty(userId)) {
            return false;
        }

        return this.userService.updateUserBrief(brief,userId);

    }
}
