package com.diandou.authority.service.impl;

import com.diandou.authority.vmodel.AuthModel;
import com.diandou.common.util.StringUtil;
import com.diandou.enumerable.AuthStatusEnum;
import com.diandou.user.dao.IUserDao;
import com.diandou.authority.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 胡志洁 on 2016/5/8.
 */

@Service("AuthorityService")
public class AuthorityService implements IAuthorityService {

    @Autowired
    private IUserDao userDao;

    @Override
    public AuthModel loginAuthority(String mobile,String password) {

        String userId = this.userDao.userLogin(mobile,password);
        String loginToken = userId;
        if(!StringUtil.isNullOrEmpty(loginToken) && !StringUtil.isNullOrEmpty(userId)){
            return new AuthModel.Builder().authStatus(AuthStatusEnum.login_pass).sessionToken(loginToken).userId(userId).build();
        }
        else{
            return new AuthModel.Builder().authStatus(AuthStatusEnum.login_fail).build();
        }
    }

    @Override
    public AuthModel loginAuthorityFailed() {
        return new AuthModel.Builder().authStatus(AuthStatusEnum.login_fail).build();
    }


}
