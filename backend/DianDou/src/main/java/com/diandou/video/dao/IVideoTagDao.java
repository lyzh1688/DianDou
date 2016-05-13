package com.diandou.video.dao;

import com.diandou.video.entity.VideoTag;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public interface IVideoTagDao {
    public List<VideoTag> getVideoTags(String videoId);

    public List<VideoTag> getVideosTags(List<String> videoIds);

}
