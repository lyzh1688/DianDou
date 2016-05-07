package com.diandou.video.vmodel;

import com.diandou.video.entity.Video;
import com.diandou.video.entity.VideoTag;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public class VideoModel {
    private Video video;
    private List<VideoTag> videoTag;

    public VideoModel(Video video, List<VideoTag> videoTag) {
        this.video = video;
        this.videoTag = videoTag;
    }

    public Video getVideo() {
        return video;
    }

    public List getVideoTag() {
        return videoTag;
    }
}
