package com.diandou.user.dao.impl;

import com.diandou.common.Authority.EncodePassword;
import com.diandou.common.option.InOption;
import com.diandou.common.option.LikeOption;
import com.diandou.common.option.PagenationOption;
import com.diandou.enumerable.AuthStatusEnum;
import com.diandou.user.dao.IUserDao;
import com.diandou.user.entity.User;
import com.diandou.user.entity.VideoCount;
import com.diandou.user.mapper.UserMapper;
import com.diandou.user.mapper.VideoCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
@Repository("UserDao")
public class UserDao implements IUserDao {

    private final static String loginFailed = "";

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public List<VideoCount> getVideoCounts(List<String> userIds) {

        List<VideoCount> videoCounts = null;

        String sql = "select i.owner_id ,count(1) as count from " +
                    "dat_video_info i where i.owner_id in " +
                    new InOption(userIds).genOptionCode() +
                    " group by i.owner_id";
        videoCounts = jdbcTemplate.query(sql,new VideoCountMapper() );

        return videoCounts;
    }

    @Override
    public User getUserInfoById(String userId) {

        String sql = "SELECT i.user_id," +
                " i.user_name," +
                " i.head_portrait," +
                " i.brief," +
                " i.mobile, " +
                " i.sex, " +
                " i.regisiter_date " +
                " from dat_user_info i " +
                " where i.user_id = ? ";

        User user = this.jdbcTemplate.queryForObject(sql ,new Object[] { userId }, new UserMapper() );

        return user;
    }

    @Override
    public User getUserInfoByMobile(String mobile) {

        String sql = "SELECT i.user_id," +
                " i.user_name," +
                " i.head_portrait," +
                " i.brief," +
                " i.mobile, " +
                " i.sex, " +
                " i.regisiter_date " +
                " from dat_user_info i " +
                " where i.mobile = ? ";

        User user = this.jdbcTemplate.queryForObject(sql ,new Object[] { mobile }, new UserMapper() );

        return user;
    }

    @Override
    public boolean updateUserInfo(User user) {

        String sql = " update dat_user_info t set t.user_name = ?,t.brief = ?,t.sex = ? " +
                    " where t.mobile = ? ";

        int affectedRows = this.jdbcTemplate.update(sql,user.getUserName(),user.getBrief(),user.getSex(),user.getMobile());

        return affectedRows != 0;
    }

    @Override
    public List<User> getUserListByRole(String pageIdx,String pageSize,String roleId) {

        List<User> userList = null;

        String sql = "SELECT i.user_id," +
                " i.user_name," +
                " i.head_portrait," +
                " i.brief," +
                " i.mobile, " +
                " i.sex, " +
                " i.regisiter_date " +
                " FROM dat_user_info i,dat_user_role r " +
                " where r.role_id = ? " +
                " and i.user_id = r.user_id " +
                " order by i.user_id " +
                new PagenationOption(pageSize,pageIdx).genOptionCode();

        userList = jdbcTemplate.query(sql,new Object[]{roleId} , new UserMapper());

        return userList;
    }

    @Override
    public List<User> getUserListByName(String pageIdx, String pageSize, String userName) {
        List<User> userList = null;

        String sql = "SELECT i.user_id," +
                " i.user_name," +
                " i.head_portrait," +
                " i.brief," +
                " i.mobile, " +
                " i.sex, " +
                " i.regisiter_date " +
                " FROM dat_user_info i  " +
                " where  i.user_name like " +
                new LikeOption(userName).genOptionCode() +
                " order by i.user_id " +
                new PagenationOption(pageSize,pageIdx).genOptionCode();

        userList = jdbcTemplate.query(sql, new UserMapper());

        return userList;
    }

    @Override
    public String userLogin(String mobile,String password) {

        String encodePasswrod = EncodePassword.encodePassword(password);

        int cnt = this.jdbcTemplate.queryForObject("select count(1) from dat_user_info where mobile = ? and password = ? ",
                new Object[] {mobile,encodePasswrod}, Integer.class);

        if(cnt > 0){

            String userId = this.jdbcTemplate.queryForObject("select user_id from dat_user_info where mobile = ? and password = ? ",
                    new Object[] {mobile,encodePasswrod}, String.class);

            return userId;
        }
        else{

            return UserDao.loginFailed;
        }
    }

    @Override
    public AuthStatusEnum userRegister(User user) {

        String sql = "select count(1) from dat_user_info where mobile = ? ";

        int cnt = this.jdbcTemplate.queryForObject(sql,new Object[] {user.getMobile()}, Integer.class);

        if(cnt > 0){
            return AuthStatusEnum.reg_failed_mobile_exists;
        }

        sql = "insert into dat_user_info(mobile,password)" +
						 " values (?,?) ";

		int affectedRows = this.jdbcTemplate.update(sql,user.getMobile(),user.getPassword());

        if(affectedRows != 0){
            return AuthStatusEnum.reg_success;

        }
        else
        {
            return AuthStatusEnum.reg_success.reg_failed_mobile_exists;
        }
    }
}
