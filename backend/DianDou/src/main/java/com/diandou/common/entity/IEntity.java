package com.diandou.common.entity;

import com.diandou.common.entity.impl.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public interface IEntity {
    public Map<String, String> getDBMapperMap();
    public String getTabName();
}
