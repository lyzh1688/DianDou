package com.diandou.video.mapper;

import com.diandou.video.entity.Video;
import com.diandou.video.entity.VideoTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class VideoTagMapper implements RowMapper<VideoTag> {
    @Override
    public VideoTag mapRow(ResultSet resultSet, int i) throws SQLException {
        VideoTag tag = new VideoTag.Builder().videoId(resultSet.getString("video_id"))
                                              .tagId(resultSet.getString("tag_id"))
                                              .tagName(resultSet.getString("tag_name"))
                                              .build();

        return tag;
    }
}
