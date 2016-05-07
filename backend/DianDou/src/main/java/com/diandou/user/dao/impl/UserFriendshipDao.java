package com.diandou.user.dao.impl;

import com.diandou.common.option.InOption;
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
    public boolean follow(String selfId, String targeId) {
        return false;
    }

}
