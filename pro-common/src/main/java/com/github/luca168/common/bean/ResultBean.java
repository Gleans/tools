package com.github.luca168.common.bean;

import com.github.luca168.common.enums.ResultCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = -1;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    public ResultBean<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultBean<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultBean<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static ResultBean<Object> ok(Object data){
        return new ResultBean<>(data);
    }


    public  static ResultBean<Object> error(ResultCodeEnum resultCode){
        ResultBean<Object> result=new ResultBean<>();
        result.setCode(resultCode.code());
        result.setMsg(resultCode.message());
        return result;
    }
}