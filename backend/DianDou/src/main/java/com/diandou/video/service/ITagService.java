package com.diandou.video.service;

import com.diandou.video.entity.TagInfo;
import com.diandou.video.vmodel.TagModel;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
public interface ITagService {


    public List<TagInfo> getTagListByType(String tagType);
    public List<TagInfo> getTagListByType();
    public List<TagInfo> getTagListByName(String tagName);
    public List<TagModel> getTagModelListByType(String tagId,int count);
}
