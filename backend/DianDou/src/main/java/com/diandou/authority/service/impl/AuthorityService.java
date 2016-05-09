package com.diandou.authority.service.impl;

import com.diandou.authority.vmodel.AuthModel;
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
    public AuthModel loginAuthority(String mobile) {

        String userId = this.userDao.userLogin(mobile);
        String loginToken = userId;
        if( null != loginToken){
            return new AuthModel.Builder().authStatus(AuthStatusEnum.pass).sessionToken(loginToken).userId(userId).build();
        }
        else{
            return new AuthModel.Builder().authStatus(AuthStatusEnum.fail).build();
        }
    }

    @Override
    public AuthModel loginAuthorityFailed() {
        return new AuthModel.Builder().authStatus(AuthStatusEnum.fail).build();
    }


}
