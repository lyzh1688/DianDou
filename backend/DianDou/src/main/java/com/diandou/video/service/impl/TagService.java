package com.diandou.video.service.impl;

import com.diandou.video.dao.ITagDao;
import com.diandou.video.dao.IVideoDao;
import com.diandou.video.entity.TagInfo;
import com.diandou.video.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
@Service("TagService")
public class TagService implements ITagService {

    @Autowired
    private ITagDao tagDao;

    @Override
    public List<TagInfo> getTagListByType() {

        String defaultTagType = "0";

        return this.tagDao.getTagListByType(defaultTagType);
    }

    @Override
    public List<TagInfo> getTagListByType(String tagType) {
        return this.tagDao.getTagListByType(tagType);
    }
}
