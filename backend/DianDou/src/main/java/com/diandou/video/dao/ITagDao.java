package com.diandou.video.dao;

import com.diandou.video.entity.TagInfo;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
public interface ITagDao {
    List<TagInfo> getTagListByType(String tagType);

    List<TagInfo> getTagListByName(String tagName);
}
