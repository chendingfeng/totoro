package com.dingfeng.totoro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 权限实体类
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Right extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private String description;
    private String details;
    
	public List<Integer> getUrlList(){
		List<Integer> list = new ArrayList<Integer>();
		if (details != null) {
			String rl[] = details.trim().split(",");
			for (int i = 0; i < rl.length; i++) {
				String[] numbers = rl[i].trim().split("-");
				try {
					int start = Integer.valueOf(numbers[0]);
					int end = Integer.valueOf(numbers[numbers.length-1]);
					for (int j = start; j <= end; j++) {
						list.add(j);
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}