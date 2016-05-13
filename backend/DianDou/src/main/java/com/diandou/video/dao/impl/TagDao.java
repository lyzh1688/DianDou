package com.diandou.video.dao.impl;

import com.diandou.video.dao.ITagDao;
import com.diandou.video.entity.TagInfo;
import com.diandou.video.mapper.TagInfoMapper;
import com.diandou.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
@Repository("TagDao")
public class TagDao implements ITagDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TagInfo> getTagListByType(String tagType){
        List<TagInfo> tagList = null;

        String sql = "select tag_id,tag_name,tag_type,image from prm_video_tag where tag_type = ?";

        tagList = jdbcTemplate.query(sql,new Object[]{tagType} , new TagInfoMapper() );

        return tagList;
    }

}
