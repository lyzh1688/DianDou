package com.diandou.video.service.impl;

import com.diandou.video.dao.ITagDao;
import com.diandou.video.dao.IVideoDao;
import com.diandou.video.entity.TagInfo;
import com.diandou.video.entity.Video;
import com.diandou.video.service.ITagService;
import com.diandou.video.vmodel.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
@Service("TagService")
public class TagService implements ITagService {

    @Autowired
    private ITagDao tagDao;

    @Autowired
    private IVideoDao videoDao;

    @Override
    public List<TagInfo> getTagListByType() {

        String defaultTagType = "0";

        return this.tagDao.getTagListByType(defaultTagType);
    }

    @Override
    public List<TagInfo> getTagListByName(String tagName) {
        return this.tagDao.getTagListByName(tagName);
    }

    /*
    * YUEZHI LIU
    * 2016.05.17
    * @Fields:tagId : 收费视频分类
    * @Description:获取所有基本视频分类，以及各个基本分类与收费种类的交集视频
    * */
    @Override
    public List<TagModel> getTagModelListByType(String tagId,int count) {

        List<TagModel> tagModelList = new ArrayList<TagModel>();

        List<TagInfo> tagInfoList = this.getTagListByType();

        for (TagInfo tagInfo:tagInfoList) {
            List<String> tagIDs = new ArrayList<String>();
            tagIDs.add(tagInfo.getTagId());
            tagIDs.add(tagId);
            List<Video> videoList = this.videoDao.getVideoListByTags(tagIDs,count);
            tagModelList.add(new TagModel(tagInfo,videoList));
        }

        return tagModelList;
    }

    @Override
    public List<TagInfo> getTagListByType(String tagType) {
        return this.tagDao.getTagListByType(tagType);
    }
}
