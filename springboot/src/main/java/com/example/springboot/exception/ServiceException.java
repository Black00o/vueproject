package com.example.springboot.exception;

import lombok.Getter;

/**
 * 功能：
 * 作者：lhy
 * 日期：2023/8/22 12:40
 */
@Getter
public class ServiceException extends RuntimeException {

    private final String code;

    public ServiceException(String msg) {
        super(msg);
        this.code = "500";
    }


    public ServiceException(String code,String msg) {
        super(msg);
        this.code = code;
    }

}