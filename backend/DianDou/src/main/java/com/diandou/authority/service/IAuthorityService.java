package com.diandou.authority.service;

import com.diandou.authority.vmodel.AuthModel;

import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/8.
 */
public interface IAuthorityService {

    public AuthModel loginAuthority(String mobile,String password);

    public AuthModel loginAuthorityFailed();
}
