package com.diandou.common.Authority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/9.
 */
public class TokenContainer {

    private static Object syncRoot = new Object();

    private Map<String,String> tokenUserCache;

    private static TokenContainer instance;

    public Map<String, String> getTokenUserCache() {
        return tokenUserCache;
    }

    private TokenContainer(){
        this.tokenUserCache = new HashMap<String, String>();
    }

    public static TokenContainer GetInstance(){
        if(TokenContainer.instance == null){
            synchronized (syncRoot){
                if(TokenContainer.instance == null){
                    TokenContainer.instance = new TokenContainer();
                }
            }
        }

        return TokenContainer.instance;
    }

}
