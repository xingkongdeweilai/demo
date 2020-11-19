package com.imooc.demo.demo30.vo;

import com.imooc.demo.demo30.enums.ResponseEnum;

import java.io.Serializable;

public class CommonVO implements Serializable {
    private static final long serialVersionUID = 3932638847642532474L;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应描述
     */
    private String message;

    /**
     * 响应体
     * @return
     */
    private Object body;

    public static CommonVO ok() {
        CommonVO result = new CommonVO();
        result.setCode(ResponseEnum.SUCCESS.getCode());
        result.setMessage(ResponseEnum.SUCCESS.getMessage());
        return result;
    }

    public static CommonVO errorRequestBody() {
        CommonVO result = new CommonVO();
        result.setCode(ResponseEnum.REQUEST_BODY_ERROR.getCode());
        result.setMessage(ResponseEnum.REQUEST_BODY_ERROR.getMessage());
        return result;
    }

    public static CommonVO errorRequestUri() {
        CommonVO result = new CommonVO();
        result.setCode(ResponseEnum.REQUEST_URI_ERROR.getCode());
        result.setMessage(ResponseEnum.REQUEST_URI_ERROR.getMessage());
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonVO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
