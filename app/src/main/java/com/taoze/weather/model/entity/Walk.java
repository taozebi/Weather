package com.taoze.weather.model.entity;

import java.io.Serializable;

/**
 * 步行
 * Created by Taoze on 2018/5/31.
 */

public class Walk implements Serializable {

    /**
     * 步数
     */
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
