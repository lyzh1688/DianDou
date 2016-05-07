package com.diandou.video.service;

import com.diandou.video.entity.Video;
import com.diandou.video.vmodel.VideoModel;

import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public interface IVideoService {

    public Map<String,VideoModel> getLatestVideoListByOwnerList(List<String> ownerList);
    public List<VideoModel> getVideoListByTag(String pageIdx, String pageSize, String tagId);
    public List<VideoModel> getVideoListByOwner(String pageIdx, String pageSize, String ownerId);
}
