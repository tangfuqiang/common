package com.tfq.common.entiy;

import com.tfq.common.enums.ResultEnum;

import java.io.Serializable;

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

    public Result success(T data){
        return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMsg(),data);
    }

    public Result success(String msg,T data){
        return new Result(ResultEnum.SUCCESS.getCode(),msg,data);
    }

    public Result error(T data){
        return new Result(ResultEnum.ERROR.getCode(),ResultEnum.ERROR.getMsg(),data);
    }

    public Result error(String msg,T data){
        return new Result(ResultEnum.ERROR.getCode(),msg,data);
    }

    public Result error(String code,String msg,T data){
        return new Result(code,msg,data);
    }




}
