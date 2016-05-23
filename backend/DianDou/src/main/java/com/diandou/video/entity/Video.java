package com.diandou.video.entity;

import com.diandou.annotation.DBMapper;
import com.diandou.annotation.TBMapper;
import com.diandou.common.entity.impl.BaseEntity;

import java.sql.Timestamp;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public class Video {

    private final String videoId;

    private final String videoName;

    private final String videoLink;

    private final String ownerId;

    private final String ownerName;

    private final float totalTime;

    private final String brief;

    private final String status;

    private final String videoPic;

    private final String uploadDate;

    public String getVideoId() {
        return videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public String getBrief() {
        return brief;
    }

    public String getStatus() {
        return status;
    }

    public String getVideoPic() {
        return videoPic;
    }

    public String getUploadDate() { return uploadDate;}
    private Video(Builder builder){
        this.videoId = builder.videoId;
        this.videoName = builder.videoName;
        this.videoLink = builder.videoLink;
        this.ownerId = builder.ownerId;
        this.ownerName = builder.ownerName;
        this.totalTime = builder.totalTime;
        this.brief = builder.brief;
        this.status = builder.status;
        this.videoPic = builder.videoPic;
        this.uploadDate = builder.uploadDate;
    }


    public static class Builder{
        private String videoId;
        private String videoName;
        private String videoLink;
        private String ownerId;
        private float totalTime;
        private String brief;
        private String status;
        private String videoPic;
        private String uploadDate;
        private String ownerName;

        public Builder videoId(String videoId){
            this.videoId = videoId;
            return this;
        }

        public Builder videoName(String videoName){
            this.videoName = videoName;
            return this;
        }

        public Builder videoLink(String videoLink){
            this.videoLink = videoLink;
            return this;
        }

        public Builder ownerId(String ownerId){
            this.ownerId = ownerId;
            return this;
        }

        public Builder ownerName(String ownerName){
            this.ownerName = ownerName;
            return this;
        }

        public Builder totalTime(float totalTime){
            this.totalTime = totalTime;
            return this;
        }

        public Builder brief(String brief){
            this.brief = brief;
            return this;
        }

        public  Builder status(String status){
            this.status = status;
            return this;
        }

        public Builder videoPic(String videoPic){
            this.videoPic = videoPic;
            return this;
        }

        public Builder uploadDate(String uploadDate){
            this.uploadDate = uploadDate;
            return this;
        }

        public Video build(){
            return new Video(this);
        }
    }

}

