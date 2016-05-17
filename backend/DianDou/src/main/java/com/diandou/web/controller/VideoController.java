package com.diandou.web.controller;

import com.diandou.annotation.Authority;
import com.diandou.common.option.FilterOption;
import com.diandou.common.util.StringUtil;
import com.diandou.video.entity.TagInfo;
import com.diandou.video.entity.Video;
import com.diandou.video.service.ITagService;
import com.diandou.video.service.IVideoService;
import com.diandou.video.service.impl.TagService;
import com.diandou.video.service.impl.VideoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/3.
 */

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @Authority
    @RequestMapping(value = "/getVideoList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getVideoList(HttpServletRequest request){

        String tagId = request.getParameter("tagId");
        String ownerId = request.getParameter("ownerId");
        String pageIdx = request.getParameter("pageIdx");
        String pageSize = request.getParameter("pageSize");

        if(tagId != null) {
            return new Gson().toJson(this.videoService.getVideoListByTag(pageIdx,pageSize,tagId));
        }
        if(ownerId != null) {
            return new Gson().toJson(this.videoService.getVideoListByOwner(pageIdx,pageSize,ownerId));
        }

        return "";
    }
}
