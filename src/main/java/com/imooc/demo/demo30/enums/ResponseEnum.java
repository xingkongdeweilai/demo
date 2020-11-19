package com.imooc.demo.demo30.enums;

public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS("0", "success")
    /**
     * 请求体异常
     */
    , REQUEST_BODY_ERROR("-1", "请求体异常")
    /**
     * 请求地址异常
     */
    , REQUEST_URI_ERROR("-2", "请求地址异常")
    ;

    /**
     * 响应code码
     */
    private final String code;

    /**
     * 响应详细描述
     */
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
