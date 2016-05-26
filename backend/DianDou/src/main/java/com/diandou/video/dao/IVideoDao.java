package com.diandou.video.dao;

import com.diandou.video.entity.Video;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public interface IVideoDao {

    public List<Video> getLatestVideoListByOwnerList(List<String> ownerList);

    public List<Video> getVideoListByTags(List<String> tags,int count);

    public List<Video> getVideoListByTag(String pageIdx,String pageSize,String tagId);

    public List<Video> getVideoListByOwner(String pageIdx,String pageSize,String ownerId);

    public List<Video> getVideoListByName(String pageIdx,String pageSize,String videoName);

    public Video getVideoById(String videoId);
}
