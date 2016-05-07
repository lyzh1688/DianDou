package com.diandou.web.controller;

import com.diandou.user.service.IUserService;
import com.diandou.video.service.IVideoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getUserList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getVideoList(HttpServletRequest request){

        String roleId = request.getParameter("roleId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");

        if(roleId != null) {
            return new Gson().toJson(this.userService.getUserListByRole(pageIdx,pageSize,roleId));
        }

        return "";
    }
}
