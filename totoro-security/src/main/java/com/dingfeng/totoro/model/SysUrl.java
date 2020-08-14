package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

/**
 * @Description: 系统接口实体类
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class SysUrl extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String url;
    private String description;
    private int modify;				// 是否修改接口
}