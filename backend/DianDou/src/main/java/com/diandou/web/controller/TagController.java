package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.common.util.StringUtil;
import com.diandou.video.entity.TagInfo;
import com.diandou.video.service.ITagService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public String searchTagList(HttpServletRequest request){

        List<TagInfo> tagInfoList = null;
        String tagName = request.getParameter("tagName");

        tagInfoList = this.tagService.getTagListByName(tagName);

        return new Gson().toJson(tagInfoList);
    }

    @Authority
    @RequestMapping(value = "/getTagList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTagList(HttpServletRequest request){

        List<TagInfo> tagInfoList = null;
        String tagType = request.getParameter("tagType");
        if(StringUtil.isNullOrEmpty(tagType)){
            tagInfoList = this.tagService.getTagListByType();
        }
        else{
            tagInfoList = this.tagService.getTagListByType(tagType);
        }

        return new Gson().toJson(tagInfoList);
    }
}
