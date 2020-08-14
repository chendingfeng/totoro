package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

/**
 * @Description: 角色权限关系实体
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class RoleRight extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long roleId;
    private Long rightId;

    public RoleRight(Long id) {
    	this.id = id;
    }
}