package com.diandou.user.entity;

/**
 * Created by 胡志洁 on 2016/6/1.
 */
public class TagInfo {

    private String tagId;

    private String tagName;

    public TagInfo(String tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }
}
