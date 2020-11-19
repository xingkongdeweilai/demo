package com.imooc.demo.demo30.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum UriEnum {

    /**
     * 获取圆的面积
     */
    GET_CIRCLE_AREA("getCircleArea")
    /**
     * 发送消息
     */
    , SEND_MESSAGE("sendMessage")
    /**
     * 标识null
     */
    , NULL(null)
    ;

    /**
     * 接口地址
     */
    private final String uri;

    private final static Map<String, UriEnum> URI_ENUM_MAP = new HashMap<>();

    UriEnum(String uri) {
        this.uri = uri;
    }

    static {
        Arrays.stream(UriEnum.values()).forEach(uriEnum -> URI_ENUM_MAP.put(uriEnum.getUri(), uriEnum));
    }

    public static UriEnum getInstance(String uri) {
        if (URI_ENUM_MAP.containsKey(uri)) {
            return URI_ENUM_MAP.get(uri);
        }
        return NULL;
    }

    public String getUri() {
        return uri;
    }

}
