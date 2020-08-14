package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户信息实体类
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class User extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private Byte userType;					// 学校、机构用户（管理员或教师）、家长
    private String relativeId;				// 关联的学校或机构代码
    private String head;					// 用户头像
    private Byte admin;						// 是否超级管理员
    private Byte enabled;					// 禁用状态
    private Byte expired;					// 是否过期
    private Byte locked;					// 是否锁定
    @Builder.Default
    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime = new Date();	// 创建时间
    private String createUser;				// 创建人员

    public User(String userName){
    	this.username = userName;
    }
}