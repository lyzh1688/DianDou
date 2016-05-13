package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.common.util.StringUtil;
import com.diandou.enumerable.FollowActionEnum;
import com.diandou.user.service.IUserFriendshipService;
import com.diandou.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 胡志洁 on 2016/5/13.
 */
@Controller
@RequestMapping("/friendship")
public class FriendShipController {

    @Autowired
    private IUserFriendshipService userFriendshipService;

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

}
