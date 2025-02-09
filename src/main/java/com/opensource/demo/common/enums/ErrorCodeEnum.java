package com.opensource.demo.common.enums;

/**
 * 异常错误码枚举类
 */
public enum ErrorCodeEnum {

    SUCCESS(200, "请求成功"),
    SYSTEM_ERROR(500, "系统繁忙,请稍后重试"),
    INVALID_PARAMS(401, "非法请求参数"),
    FORBID_OPERATE(404, "无权限操作"),
    ;
    private final int code;
    private final String message;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
