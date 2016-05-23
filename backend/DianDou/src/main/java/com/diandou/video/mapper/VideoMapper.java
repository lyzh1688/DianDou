package com.diandou.video.mapper;

import com.diandou.video.entity.Video;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public class VideoMapper implements RowMapper<Video> {

    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public Video mapRow(ResultSet rs, int rowNum) throws SQLException {

        // TODO Auto-generated method stub

        Video obj = new Video.Builder().videoId(rs.getString("video_id"))
                                        .videoName(rs.getString("video_name"))
                                        .videoLink(rs.getString("video_link"))
                                        .ownerId(rs.getString("owner_id"))
                                        .totalTime(rs.getFloat("total_time"))
                                        .brief(rs.getString("brief"))
                                        .status(rs.getString("status"))
                                        .videoPic(rs.getString("video_pic"))
                                        .uploadDate(VideoMapper.df.format(rs.getTimestamp("upload_date")))
                                        .ownerName(rs.getString("ownerName")).build();

        return obj;
    }

}
