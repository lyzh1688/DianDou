package com.diandou.common.option;

import com.diandou.common.util.StringUtil;

/**
 * Created by 胡志洁 on 2016/5/5.
 */
public class PagenationOption extends Option{

    private final int pageSize;

    private final int pageIndex;

    private boolean flag = false;
    public PagenationOption(String pageSize ,String pageIndex) {
        if(StringUtil.isNullOrEmpty(pageSize) || StringUtil.isNullOrEmpty(pageIndex)){
            this.flag = false;
            this.pageSize = 0;
            this.pageIndex = 0;
        }
        else {
            this.flag = true;
            this.pageSize = Integer.parseInt(pageSize);
            this.pageIndex = Integer.parseInt(pageIndex);
        }
    }

    @Override
    public String genOptionCode() {

        if(this.flag == false){
            return "";
        }

        String sql = " limit " + Integer.toString(this.pageIndex * this.pageSize) + "," + Integer.toString(this.pageSize);

        return sql;
    }
}
