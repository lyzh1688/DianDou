package com.diandou.video.vmodel;

import com.diandou.video.entity.TagInfo;
import com.diandou.video.entity.Video;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/17.
 */
public class TagModel {

    private TagInfo tagInfo;
    private List<Video> videoList;

    public TagModel(TagInfo tagInfo, List<Video> videoList) {
        this.tagInfo = tagInfo;
        this.videoList = videoList;
    }

    public TagInfo getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(TagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }


}
