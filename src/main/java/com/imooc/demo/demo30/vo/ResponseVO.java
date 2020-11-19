package com.imooc.demo.demo30.vo;

import java.io.Serializable;

public class ResponseVO implements Serializable {
    private static final long serialVersionUID = -5726200696026255631L;

    /**
     * 接收响应的用户
     */
    private String receiveUserName;

    /**
     * 响应对象
     */
    private CommonVO commonVO;

    public ResponseVO() {
    }

    public ResponseVO(String receiveUserName, CommonVO commonVO) {
        this.receiveUserName = receiveUserName;
        this.commonVO = commonVO;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public CommonVO getCommonVO() {
        return commonVO;
    }

    public void setCommonVO(CommonVO commonVO) {
        this.commonVO = commonVO;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "receiveUserName='" + receiveUserName + '\'' +
                ", commonVO=" + commonVO +
                '}';
    }
}
