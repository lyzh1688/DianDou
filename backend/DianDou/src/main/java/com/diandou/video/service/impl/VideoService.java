package com.diandou.video.service.impl;

import com.diandou.video.dao.IVideoDao;
import com.diandou.video.dao.impl.VideoTagDao;
import com.diandou.video.entity.Video;
import com.diandou.video.entity.VideoTag;
import com.diandou.video.service.IVideoService;
import com.diandou.video.vmodel.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
@Service("VideoService")
public class VideoService implements IVideoService {

    @Autowired
    private IVideoDao videoDao;

    @Autowired
    private VideoTagDao videoTagDao;

    private Map<String,List<VideoTag>> groupVideoTag(List<VideoTag> videosTags){

        Map<String,List<VideoTag>> groupedVideoTag = new HashMap<String, List<VideoTag>>();

        if(videosTags != null && videosTags.size() > 0){
            for (VideoTag videoTag: videosTags) {
                if(!groupedVideoTag.containsKey(videoTag.getVideoId())){
                    groupedVideoTag.put(videoTag.getVideoId(),new ArrayList<VideoTag>());
                    groupedVideoTag.get(videoTag.getVideoId()).add(videoTag);
                }
                else{
                    groupedVideoTag.get(videoTag.getVideoId()).add(videoTag);
                }
            }
        }

        return groupedVideoTag;
    }

    private List<VideoModel> getVideosTagsList(List<Video> videos){
        List<String> videoIdList = new ArrayList<String>();

        for (Video video:videos) {
            videoIdList.add(video.getVideoId());
        }

        List<VideoTag> videosTags = this.videoTagDao.getVideosTags(videoIdList);

        List<VideoModel> videoModelList = new ArrayList<VideoModel>();

        Map<String,List<VideoTag>> groupedVideoTag = this.groupVideoTag(videosTags);

        for (Video video:videos) {
            videoModelList.add(new VideoModel(video,groupedVideoTag.get(video.getVideoId())));
        }

        return videoModelList;
    }

    @Override
    public List<Video> getVideoListByTag(String tagId) {
        return this.videoDao.getVideoListByTag("0","-1",tagId);
    }

    @Override
    public Map<String,VideoModel> getLatestVideoListByOwnerList(List<String> ownerList) {

        List<Video> videos = this.videoDao.getLatestVideoListByOwnerList(ownerList);

        List<VideoModel> videoModelList = this.getVideosTagsList(videos);

        Map<String,VideoModel> latestUploadVideoMap = new HashMap<String, VideoModel>();

        for(VideoModel videoModel : videoModelList){
            latestUploadVideoMap.put(videoModel.getVideo().getOwnerId(),videoModel);
        }

        return latestUploadVideoMap;
    }

    @Override
    public List<VideoModel> getVideoListByTag(String pageIdx, String pageSize, String tagId) {
        
        List<Video> videos = this.videoDao.getVideoListByTag(pageIdx,pageSize,tagId);

        List<VideoModel> videoModelList = this.getVideosTagsList(videos);

        return videoModelList;
    }

    @Override
    public List<Video> getVideoListByTags(List<String> tags, int count) {
        return this.videoDao.getVideoListByTags(tags,count);
    }

    @Override
    public List<VideoModel> getVideoListByOwner(String pageIdx, String pageSize, String ownerId) {

        List<Video> videos = this.videoDao.getVideoListByOwner(pageIdx,pageSize,ownerId);

        List<VideoModel> videoModelList = this.getVideosTagsList(videos);

        return videoModelList;
    }

    @Override
    public List<VideoModel> getVideoListByName(String pageIdx, String pageSize, String videoName) {
        List<Video> videos = this.videoDao.getVideoListByName(pageIdx,pageSize,videoName);

        List<VideoModel> videoModelList = this.getVideosTagsList(videos);

        return videoModelList;
    }

    @Override
    public Video getVideoById(String videoId) {
        return this.videoDao.getVideoById(videoId);
    }


}
