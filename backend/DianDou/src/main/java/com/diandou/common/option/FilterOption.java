package com.diandou.common.option;

import com.diandou.common.entity.impl.BaseEntity;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/3.
 * 本类用于生产where子句，目前只支持“等号”
 */
public class FilterOption extends Option {

    private BaseEntity entity;

    public FilterOption(BaseEntity entity) {
        if(entity == null){
            this.entity = new BaseEntity();
        }
        this.entity = entity;
    }

    @Override
    public String genOptionCode() {
        String tableName = "";
        Map<String, String> dbMapperValMap = this.entity.getDBMapperMap();
        Iterator<Map.Entry<String, String>> entries = dbMapperValMap.entrySet().iterator();
        String options = "";
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            String sql = tableName + entry.getKey() + Option.eq + "'" + entry.getValue() + "'" + Option.and;
            options += sql;
        }
        return options;
    }
}
