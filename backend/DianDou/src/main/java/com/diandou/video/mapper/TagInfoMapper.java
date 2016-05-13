package com.diandou.video.mapper;

import com.diandou.video.entity.TagInfo;
import com.diandou.video.entity.VideoTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/5/12.
 */
public class TagInfoMapper implements RowMapper<TagInfo> {
    @Override
    public TagInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        TagInfo tag = new TagInfo.Builder().tagId(resultSet.getString("tag_id"))
                .tagName(resultSet.getString("tag_name"))
                .tagType(resultSet.getString("tag_type"))
                .imageSrc(resultSet.getString("image"))
                .build();

        return tag;
    }
}