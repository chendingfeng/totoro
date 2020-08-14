package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

/**
 * @Description: 权限项目实体(权限与权限项目为一对多关系)
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class RightItem extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long rightId;
    private Long urlId;
    
    public RightItem(Long id) {
    	this.id = id;
    }
}