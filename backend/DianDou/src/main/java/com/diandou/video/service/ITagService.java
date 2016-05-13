package com.diandou.video.service;

import com.diandou.video.entity.TagInfo;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
public interface ITagService {

    public List<TagInfo> getTagListByType(String tagType);
    public List<TagInfo> getTagListByType();

}
