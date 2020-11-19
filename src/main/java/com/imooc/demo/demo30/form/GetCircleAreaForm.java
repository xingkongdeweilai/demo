package com.imooc.demo.demo30.form;

import java.io.Serializable;

public class GetCircleAreaForm implements Serializable {
    private static final long serialVersionUID = -5796574745635947268L;

    private Double radius;

    public GetCircleAreaForm(Double radius) {
        this.radius = radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "GetCircleAreaForm{" +
                "radius=" + radius +
                '}';
    }
}
