package com.diandou.video.entity;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
public class TagInfo {
    private final String tagId;

    private final String tagName;

    private final String tagType;

    private final String imageSrc;

    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagType() {
        return tagType;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    private TagInfo(Builder builder){
        this.tagId = builder.tagId;
        this.tagName = builder.tagName;
        this.tagType = builder.tagType;
        this.imageSrc = builder.imageSrc;
    }

    public static class Builder{

        private String tagId;

        private String tagName;

        private String tagType;

        private String imageSrc;

        public Builder tagId(String tagId){
            this.tagId = tagId;
            return this;
        }

        public Builder tagName(String tagName){
            this.tagName = tagName;
            return this;
        }

        public Builder tagType(String tagType){
            this.tagType = tagType;
            return this;
        }

        public Builder imageSrc(String imageSrc){
            this.imageSrc = imageSrc;
            return this;
        }

        public TagInfo build(){
            return new TagInfo(this);
        }
    }


}
