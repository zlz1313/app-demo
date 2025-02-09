package com.opensource.demo.common.model;

import com.opensource.demo.common.enums.ErrorCodeEnum;
import com.opensource.demo.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求返回结果模型
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private T data;
    private Integer code;
    private String msg;
    private String time;

    public static <T> Result<T> succeed(String msg) {
        return of(null, ErrorCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T data) {
        return of(data, ErrorCodeEnum.SUCCESS);
    }

    public static <T> Result<T> failed(BusinessException e) {
        return of(null, e.getCode(), e.getMessage());
    }

    public static <T> Result<T> failed(T data, String msg) {
        return of(data, ErrorCodeEnum.SYSTEM_ERROR.getCode(), msg);
    }

    private static <T> Result<T> of(T data, Integer code, String msg) {
        return new Result<>(data, code, msg, new Date().toString());
    }

    public static <T> Result<T> of(T data, ErrorCodeEnum errorCodeEnum) {
        return of(data, errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }
}
