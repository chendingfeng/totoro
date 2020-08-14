package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户权限VO类
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UserRight extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private String token;
	private Long userId;
	private String userName;
	private long userType;
	private String mpOpenId;			// 如果有公众号则填写公众号id
	private List<SysUrl> urls;
	
	public UserRight(String token) {
		this.token = token;
	}
}
