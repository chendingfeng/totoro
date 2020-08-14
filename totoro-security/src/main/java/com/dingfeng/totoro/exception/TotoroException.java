package com.dingfeng.totoro.exception;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 自定义异常
 * @Author: chendingfengmail@163.com
 * @Date: 2020/8/9
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TotoroException extends Exception{
    private static final long serialVersionUID = 1L;
    private int error;
    private String message;

    public TotoroException(int error, String message) {
        this.error = error;
        this.message = message;
    }
}
