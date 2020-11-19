package com.imooc.demo.demo30.form;

import java.io.Serializable;

/**
 * 请求form
 */
public class RequestForm implements Serializable {

    private static final long serialVersionUID = 8123143540097142399L;

    /**
     * 请求uri
     */
    private String uri;

    /**
     * 请求对象
     */
    private Object requestBody;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public String toString() {
        return "RequestForm{" +
                "uri='" + uri + '\'' +
                ", requestBody=" + requestBody +
                '}';
    }
}
