package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

/**
 * @Description: 系统角色实体
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private String description;
    private Long userType;
    private String relativeId;
    private String createUser;

    public Role(Long id){
    	this.id = id;
    }
    
    public Role(Long id, String name, String description){
    	this.id = id;
    	this.name = name;
    	this.description = description;
    }
}