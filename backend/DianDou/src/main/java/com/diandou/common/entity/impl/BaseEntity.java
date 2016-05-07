package com.diandou.common.entity.impl;

import com.diandou.annotation.DBMapper;
import com.diandou.annotation.TBMapper;
import com.diandou.common.entity.IEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 胡志洁 on 2016/5/3.
 */
public class BaseEntity implements IEntity{

    public Map<String, String> getDBMapperMap() {
        Map<String, String> dbMapperValMap = new HashMap<String, String>();
        Field[] field = this.getClass().getDeclaredFields();
        try {
            // 遍历所有属性
            for (int j = 0; j < field.length; j++) {
                DBMapper dbMapper = field[j].getAnnotation(DBMapper.class);
                String fieldName = null;
                String fieldValue = null;
                if (null != dbMapper){
                    fieldName = dbMapper.FieldName();
                    // 获取属性的名字
                    String name = field[j].getName();
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method m = this.getClass().getMethod("get" + name);
                    // 调用getter方法获取属性值
                    fieldValue = (String) m.invoke(this);
                    if(fieldValue != null) {
                        dbMapperValMap.put(fieldName, fieldValue);
                    }
                }
            }

            return dbMapperValMap;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            return dbMapperValMap;
        } catch (SecurityException e) {
            e.printStackTrace();
            return dbMapperValMap;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return dbMapperValMap;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return dbMapperValMap;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return dbMapperValMap;
        }

    }

    @Override
    public String getTabName() {
        TBMapper tbMapper = this.getClass().getAnnotation(TBMapper.class);
        return tbMapper.TabName();
    }
}
