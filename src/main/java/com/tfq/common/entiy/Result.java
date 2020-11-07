package com.tfq.common.entiy;

import com.tfq.common.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 42L;

    /**
     * 类型代码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),data);
    }

    public static Result success(String msg,Object data){
        return new Result(ResultEnum.SUCCESS.getCode(),msg,data);
    }

    public static Result error(Object data){
        return new Result(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg(),data);
    }

    public static Result error(String msg,Object data){
        return new Result(ResultEnum.ERROR.getCode(),msg,data);
    }

    public static Result error(String code,String msg,Object data){
        return new Result(code,msg,data);
    }

}
