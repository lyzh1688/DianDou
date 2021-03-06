package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.common.util.StringUtil;
import com.diandou.video.entity.TagInfo;
import com.diandou.video.service.ITagService;
import com.diandou.video.vmodel.TagModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/17.
 */
@Controller
@RequestMapping("/tag")
public class TagController {


    @Autowired
    private ITagService tagService;

    @Authority
    @RequestMapping(value = "/searchTagList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TagInfo> searchTagList(HttpServletRequest request){

        List<TagInfo> tagInfoList = null;
        String tagName = request.getParameter("tagName");

        tagInfoList = this.tagService.getTagListByName(tagName);

        return tagInfoList;
    }

    @Authority
    @RequestMapping(value = "/getTagModelListByType",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TagModel> getTagModelListByType(HttpServletRequest request){

        //收费分类ID
        String tagId = request.getParameter("tagId");
        String cnt = request.getParameter("count");
        int count = 4;
        if(!StringUtil.isNullOrEmpty(cnt)){
            count = Integer.parseInt(cnt);
        }

        if(StringUtil.isNullOrEmpty(tagId)){
            return new ArrayList<TagModel>();
        }

        return this.tagService.getTagModelListByType(tagId,count);


    }

    @Authority
    @RequestMapping(value = "/getTagList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TagInfo> getTagList(HttpServletRequest request){

        List<TagInfo> tagInfoList = null;
        String tagType = request.getParameter("tagType");
        if(StringUtil.isNullOrEmpty(tagType)){
            tagInfoList = this.tagService.getTagListByType();
        }
        else{
            tagInfoList = this.tagService.getTagListByType(tagType);
        }

        return tagInfoList;
    }
}
