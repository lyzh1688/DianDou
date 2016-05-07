package com.diandou.common.option;

import java.util.List;

/**
 * Created by 胡志洁 on 2016/5/6.
 */
public class InOption extends Option{

    private final List<String> strList;
    public InOption(List<String> strList) {
        this.strList = strList;
    }

    @Override
    public String genOptionCode() {
        String returnStr = "";

        StringBuffer sb = new StringBuffer();

        if(strList == null || this.strList.size() <= 0){

            return "('')";

        }

        for(String str : this.strList)
        {
            sb.append("'");

            sb.append(str);

            sb.append("'");

            sb.append(",");
        }

        returnStr = sb.substring(0, sb.length() - 1);

        return "(" + returnStr + ")";
    }
}
