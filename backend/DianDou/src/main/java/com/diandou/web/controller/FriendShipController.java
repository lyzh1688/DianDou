package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.common.util.StringUtil;
import com.diandou.enumerable.FollowActionEnum;
import com.diandou.user.entity.User;
import com.diandou.user.service.IUserFriendshipService;
import com.diandou.user.service.IUserService;
import com.diandou.user.vmodel.UserModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/13.
 */
@Controller
@RequestMapping("/friendship")
public class FriendShipController {

    @Autowired
    private IUserFriendshipService userFriendshipService;

    @Authority
    @RequestMapping(value = "/follow",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public boolean follow(HttpServletRequest request){
        String selfId = request.getParameter("selfId");
        String targetId = request.getParameter("targetId");
        String curFollowStatus = request.getParameter("followStatus");
        FollowActionEnum followAction = null;
        if (curFollowStatus.equals("true")){
            followAction = FollowActionEnum.follow;
        }
        else {
            followAction = FollowActionEnum.nofollow;
        }
        if(StringUtil.isNullOrEmpty(selfId) || StringUtil.isNullOrEmpty(targetId)){
            return false;
        }

        return this.userFriendshipService.follow(selfId,targetId, followAction);

    }

    @Authority
    @RequestMapping(value = "/getFriendsByUserId",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<UserModel> getFriendsByUserId(HttpServletRequest request){
        String selfId = request.getParameter("selfId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");
        return this.userFriendshipService.getFriendsByUserId(pageIdx,pageSize,selfId);
    }
}
