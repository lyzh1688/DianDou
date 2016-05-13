package com.diandou.user.dao.impl;

import com.diandou.common.option.InOption;
import com.diandou.enumerable.FollowActionEnum;
import com.diandou.user.dao.IUserFriendshipDao;
import com.diandou.user.entity.FriendCount;
import com.diandou.user.mapper.FriendCountMapper;
import com.diandou.video.mapper.VideoTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Repository("UserFriendshipDao")
public class UserFriendshipDao implements IUserFriendshipDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<String> getFriendIdList(String userId) {

        List<String> friendIdList = null;

        String sql = " select t.user_id from " +
                " (select t1.user2_id as user_id " +
                " from dat_user_friendship t1 where t1.agree_flag1 = 1 and t1.user1_id = ? " +
                " union all " +
                " select t2.user1_id as user_id " +
                " from dat_user_friendship t2 where t2.agree_flag2 = 1 and t2.user2_id = ?) t  ";

        friendIdList = this.jdbcTemplate.queryForList(sql,new Object[]{userId,userId}, String.class);

        return friendIdList;
    }

    @Override
    public int getFriendCount(String userId) {

        int cnt = this.jdbcTemplate.queryForObject("select count(1) " +
                                                        "from dat_user_friendship t " +
                                                        "where (t.user1_id = ? or t.user2_id = ?)" +
                                                        "and t.agree_flag1 = '1' and agree_flag2 = '1'",
                                                        new Object[]{userId,userId},
                                                        Integer.class);

        return cnt;
    }

    @Override
    public List<FriendCount> getFriendCounts(List<String> userIds) {

        List<FriendCount> friendCountList = new ArrayList<FriendCount>();

        String sql = "select a.user_id,sum(cnt) as count from " +
                        "(select t1.user1_id as user_id,sum(t1.agree_flag2) as cnt from dat_user_friendship t1 " +
                        "where t1.user1_id in " +
                        new InOption(userIds).genOptionCode() +
                        "group by t1.user1_id " +
                        "union all " +
                        "select t2.user2_id as user_id,sum(t2.agree_flag1) as cnt from dat_user_friendship t2 " +
                        "where t2.user2_id in " +
                        new InOption(userIds).genOptionCode() +
                        "group by t2.user2_id) a " +
                        "group by user_id ";

        friendCountList = this.jdbcTemplate.query(sql,new FriendCountMapper());

        return friendCountList;
    }

    @Override
    public boolean follow(String selfId, String targeId,FollowActionEnum followAction) {

        int count = 0;
        String uid = "";
        int affectedRows = 0;
        if(selfId.compareTo(targeId) > 0){
            uid = targeId + "_" + selfId;
        }
        else
        {
            uid = selfId + "_" + targeId;
        }

        String sql = "select count(1) from dat_user_friendship t where t.uid = ? ";

        count = this.jdbcTemplate.queryForObject(sql ,new Object[] { uid }, Integer.class );

        if(count == 1){
            if(selfId.compareTo(targeId) > 0){
                //uid = targeId + "_" + selfId;
                sql = "update dat_user_friendship t set t.agree_flag2 = ?  where t.uid = ? ";
            }
            else
            {
                //uid = selfId + "_" + targeId;
                sql = "update dat_user_friendship t set t.agree_flag1 = ?  where t.uid = ? ";
            }

            affectedRows = this.jdbcTemplate.update(sql,followAction.getAction(),uid);

            return affectedRows == 0;
        }
        else if (count == 0){

            sql = "insert into dat_user_friendship(uid,user1_id,user2_id,agree_flag1,agree_flag2) " +
                    " values(?,?,?,?,?) ";

            if(selfId.compareTo(targeId) > 0){
                //uid = targeId + "_" + selfId;
                affectedRows = this.jdbcTemplate.update(sql,uid,targeId,selfId,0,followAction.getAction());
            }
            else
            {
                //uid = selfId + "_" + targeId;
                affectedRows = this.jdbcTemplate.update(sql,uid,selfId,targeId,selfId,followAction.getAction(),0);
            }
        }

        return affectedRows == 0;
    }

}
