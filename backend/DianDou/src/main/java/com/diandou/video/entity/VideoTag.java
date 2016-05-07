package com.diandou.video.entity;

import com.diandou.annotation.DBMapper;
import com.diandou.annotation.TBMapper;
import com.diandou.common.entity.impl.BaseEntity;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public class VideoTag {

    private final String videoId;

    private final String tagId;

    private final String tagName;

    public String getVideoId() {
        return videoId;
    }

    public String getTagId() {
        return tagId;
    }

    public String getTagName(){
        return this.tagName;
    }
    private VideoTag(Builder builder) {
        this.videoId = builder.videoId;
        this.tagId = builder.tagId;
        this.tagName = builder.tagName;
    }

    public static class Builder {
        private String videoId;
        private String tagId;
        private String tagName;

        public Builder videoId(String videoId){
            this.videoId = videoId;
            return this;
        }

        public Builder tagId(String tagId){
            this.tagId = tagId;
            return this;
        }

        public Builder tagName(String tagName){
            this.tagName = tagName;
            return this;
        }
        public VideoTag build(){
            return new VideoTag(this);
        }
    }

}
