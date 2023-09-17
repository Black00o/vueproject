package com.example.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** *
 * 功能：
 *
 * 作者：lhy
 * * 日期：2023/8/14 15:56
 ** */
@Data  //Get Set方法注解
@AllArgsConstructor //写好了拥有所有属性作为参数的构造函数
@NoArgsConstructor  //写好了所有的无参构造函数
@Builder  //创建了以下类的对象
public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";

    /*
    * 请求的编码可以是200 401 404 500
    * code编码表示本次请求的结果是成功还是失败
    * 如果请求失败，可以看出失败的类型是什么，是客户端出错还是服务端出错
    * */
    private String code;

    /*
    * msg表示错误的详细信息
    * */
    private String msg;

    /*
    * 数据从什么地方返回给前端
    * 就是从data传回给前端
    * 传什么类型，data的Object类型就是什么类型
    * */
    private Object data;


    public static Result success() {
        return new Result(CODE_SUCCESS, "请求成功", null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "请求成功", data);
    }

    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error() {
        return new Result(CODE_SYS_ERROR, "系统错误", null);
    }

}