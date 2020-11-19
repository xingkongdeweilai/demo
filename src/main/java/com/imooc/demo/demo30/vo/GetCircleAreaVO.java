package com.imooc.demo.demo30.vo;

import java.io.Serializable;

public class GetCircleAreaVO implements Serializable {

    private static final long serialVersionUID = 7896446103302262687L;

    private Double area;

    public GetCircleAreaVO() {
    }

    public GetCircleAreaVO(Double area) {
        this.area = area;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "GetCircleAreaVO{" +
                "area=" + area +
                '}';
    }
}
