package com.dingfeng.totoro.enums;

/**
 * @Description: 用户类型
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
public enum  UserType {
    USER_TYPE_SERVICE(1, "运营商用户");

    private int value;
    private String description;

    private UserType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
