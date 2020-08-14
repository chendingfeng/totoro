package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

/**
 * @Description: 用户角色信息实体类
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long userId;
    private Long roleId;

    public UserRole(Long id) {
    	this.id = id;
    }
    public UserRole(Long userId, Long roleId) {
    	this.userId = userId;
    	this.roleId = roleId;
    }
}