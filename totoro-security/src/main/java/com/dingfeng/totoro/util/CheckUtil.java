package com.dingfeng.totoro.util;

import java.util.Collection;

/**
 * @Description: 格式检查
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
public class CheckUtil {
    /**
     * 检查字符串是否为空或null
     * @param value
     * @return
     */
    public static boolean isNull(String value){
        if (null == value || value.trim().equals("")){
            return true;
        }
        return false;
    }

    /**
     * 检查容器是否为空或null
     * @param value
     * @return
     */
    public static boolean isNull(Collection<?> value){
        if (null == value || value.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 检查对象是否为null
     * @param value
     * @return
     */
    public static boolean isNull(Object value){
        return null == value;
    }
}
