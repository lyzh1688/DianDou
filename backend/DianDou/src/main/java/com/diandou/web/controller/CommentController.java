package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.authority.service.IAuthorityService;
import com.diandou.authority.vmodel.AuthModel;
import com.diandou.comment.entity.Comment;
import com.diandou.comment.service.ICommentService;
import com.diandou.common.Authority.TokenContainer;
import com.diandou.enumerable.AuthStatusEnum;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/15.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;


    @RequestMapping(value = "/makeComment",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Comment makeComment(HttpServletRequest request){

        String videoId = request.getParameter("videoId");
        String userId = request.getParameter("userId");
        String commentStr = request.getParameter("comment");
        Comment comment = new Comment.Builder().userId(userId).videoId(videoId).comment(commentStr).build();
        if( this.commentService.makeComment(comment)){
            return comment;
        }
        else{
            return null;
        }
    }


    @RequestMapping(value = "/getCommentsByVideo",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Comment> getCommentsByVideo(HttpServletRequest request){
        String videoId = request.getParameter("videoId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");
        return this.commentService.getCommentsByVideo(pageIdx,pageSize,videoId);

    }
}
