package com.diandou.user.mapper;

import com.diandou.user.entity.TagInfo;
import com.diandou.user.entity.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 胡志洁 on 2016/6/1.
 */
public class TagInfoMapper implements RowMapper<TagInfo> {

    @Override
    public TagInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        // TODO Auto-generated method stub

        TagInfo obj = new TagInfo(rs.getString("tag_id"),rs.getString("tag_name"));

        return obj;
    }

}
