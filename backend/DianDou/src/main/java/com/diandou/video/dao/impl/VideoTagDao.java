package com.diandou.video.dao.impl;

import com.diandou.common.option.InOption;
import com.diandou.common.util.StringUtil;
import com.diandou.video.dao.IVideoTagDao;
import com.diandou.video.entity.VideoTag;
import com.diandou.video.mapper.VideoTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Repository("VideoTagDao")
public class VideoTagDao implements IVideoTagDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VideoTag> getVideoTags(String videoId) {
        if(StringUtil.isNullOrEmpty(videoId)){
            return new ArrayList<VideoTag>();
        }

        List<VideoTag> videoTagList = null;

        String sql = "select t.video_id," +
                    " t.tag_id," +
                    " p.tag_name " +
                    " from dat_video_tag t,prm_video_tag p " +
                    " where t.tag_id = p.tag_id " +
                    " and t.video_id = ? ";

        videoTagList = this.jdbcTemplate.query(sql,new Object[]{videoId},new VideoTagMapper());

        return videoTagList;
    }

    @Override
    public List<VideoTag> getVideosTags(List<String> videoIds) {
        if(videoIds == null || videoIds.size() == 0){
            return new ArrayList<VideoTag>();
        }

        List<VideoTag> videoTagList = null;

        String sql = " select t.video_id," +
                " t.tag_id," +
                " p.tag_name " +
                " from dat_video_tag t,prm_video_tag p " +
                " where t.tag_id = p.tag_id " +
                " and t.video_id in " +
                new InOption(videoIds).genOptionCode();

        videoTagList = this.jdbcTemplate.query(sql,new VideoTagMapper());

        return videoTagList;
    }
}
