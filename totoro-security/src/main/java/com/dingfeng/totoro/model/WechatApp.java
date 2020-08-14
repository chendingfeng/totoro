package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 微信APP实体类型：指定app登录类型
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public class WechatApp extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String openId;
    private Byte type;				// OpenIdType
    
    public WechatApp() {
    	
    }
    public WechatApp(Integer id) {
    	this.id = id;
    }
	public WechatApp(String username, String openId2, int intValue) {
		this.username = username;
		this.openId = openId2;
		this.type = (byte)intValue;
	}
}