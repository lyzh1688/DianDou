package com.diandou.video.dao.impl;

import com.diandou.common.option.InOption;
import com.diandou.common.option.LikeOption;
import com.diandou.common.option.Option;
import com.diandou.common.option.PagenationOption;
import com.diandou.video.dao.IVideoDao;
import com.diandou.video.entity.Video;
import com.diandou.video.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
@Repository("VideoDao")
public class VideoDao implements IVideoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Video> getLatestVideoListByOwnerList(List<String> ownerList){
        List<Video> videoList = null;

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName" +
                " from dat_video_info i, prm_video_status s ,dat_user_info u " +
                " where " +
                " i.status = s.status_id " +
                " and i.owner_id = u.user_id " +
                " and exists " +
                " (" +
                " select owner_id,upload_date from " +
                " (select t.owner_id,max(t.upload_date) as upload_date from dat_video_info t " +
                " where t.owner_id in " +
                new InOption(ownerList).genOptionCode() +
                " group by t.owner_id) a where a.owner_id = i.owner_id " +
                " and a.upload_date = i.upload_date " +
                " )";

        videoList = jdbcTemplate.query(sql,new VideoMapper() );

        return videoList;
    }

    /*
    * YUEZHI LIU
    * 2016.05.17
    * 根据视频的多个标签获取定量的视频列表
    * */
    @Override
    public List<Video> getVideoListByTags(List<String> tags, int count) {

        List<Video> videoList = null;
        StringBuffer strBuf = new StringBuffer();
        for (String tag:tags) {
            String exists = " and exists(select 1 from dat_video_tag t where t.video_id = i.video_id" +
                    " and t.tag_id = " + tag + " )";
            strBuf.append(exists);
        }

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName" +
                " from dat_video_info i, prm_video_status s ,dat_user_info u " +
                " where i.status = s.status_id " +
                " and i.owner_id = u.user_id" +
                strBuf.toString() +
                " order by i.upload_date,i.video_id desc " +
                new PagenationOption(String.valueOf(count),"0").genOptionCode();

        videoList = jdbcTemplate.query(sql,new VideoMapper() );

        return videoList;
    }

    public List<Video> getVideoListByTag(String pageIdx,String pageSize,String tagId) {

        List<Video> videoList = null;

        String pagenationSql = "";
        if(Integer.parseInt(pageSize) > 0 && Integer.parseInt(pageIdx) >= 0){
            pagenationSql = new PagenationOption(pageSize,pageIdx).genOptionCode();
        }

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName" +
                " from dat_video_info i, prm_video_status s ,dat_user_info u " +
                " where i.status = s.status_id " +
                " and i.owner_id = u.user_id" +
                " and exists(select 1 from dat_video_tag t where t.video_id = i.video_id" +
                " and t.tag_id = ? )" +
                " order by i.upload_date,i.video_id desc " +
                pagenationSql;
                //new PagenationOption(pageSize,pageIdx).genOptionCode();

        videoList = jdbcTemplate.query(sql,new Object[]{tagId} , new VideoMapper() );

        return videoList;
    }

    @Override
    public List<Video> getVideoListByOwner(String pageIdx,String pageSize,String ownerId) {
        List<Video> videoList = null;

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName " +
                " from dat_video_info i, prm_video_status s  ,dat_user_info u " +
                " where i.status = s.status_id" +
                " and i.owner_id = u.user_id" +
                " and i.owner_id = ? " +
                " order by i.upload_date,i.video_id desc" +
                new PagenationOption(pageSize,pageIdx).genOptionCode();

        videoList = jdbcTemplate.query(sql,new Object[]{ownerId} , new VideoMapper() );
        return videoList;
    }

    @Override
    public List<Video> getVideoListByName(String pageIdx, String pageSize, String videoName) {
        List<Video> videoList = null;

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName " +
                " from dat_video_info i, prm_video_status s  ,dat_user_info u " +
                " where i.status = s.status_id" +
                " and i.owner_id = u.user_id" +
                " and i.video_name like  " +
                new LikeOption(videoName).genOptionCode() +
                " order by i.upload_date,i.video_id desc" +
                new PagenationOption(pageSize,pageIdx).genOptionCode();

        videoList = jdbcTemplate.query(sql , new VideoMapper() );
        return videoList;
    }

    @Override
    public List<Video> getOwnerVideoListByName(String pageIdx, String pageSize, String ownerId, String videoName) {
        List<Video> videoList = null;

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName " +
                " from dat_video_info i, prm_video_status s  ,dat_user_info u " +
                " where i.status = s.status_id" +
                " and i.owner_id = u.user_id" +
                " and i.owner_id = ? " +
                " and i.video_name like  " +
                new LikeOption(videoName).genOptionCode() +
                " order by i.upload_date,i.video_id desc" +
                new PagenationOption(pageSize,pageIdx).genOptionCode();

        videoList = jdbcTemplate.query(sql,new Object[]{ownerId}  , new VideoMapper() );
        return videoList;    }

    @Override
    public Video getVideoById(String videoId) {

        String sql = "select i.video_id," +
                " i.video_name," +
                " i.video_link," +
                " i.owner_id," +
                " i.total_time," +
                " i.brief," +
                " s.status_name as status," +
                " i.video_pic," +
                " i.upload_date," +
                " u.user_name as ownerName " +
                " from dat_video_info i, prm_video_status s  ,dat_user_info u " +
                " where i.status = s.status_id" +
                " and i.owner_id = u.user_id " +
                " and i.video_id = ? ";

        Video video = this.jdbcTemplate.queryForObject(sql ,new Object[] { videoId }, new VideoMapper() );

        return video;
    }
}
