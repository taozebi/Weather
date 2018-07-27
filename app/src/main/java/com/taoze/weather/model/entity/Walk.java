package com.taoze.weather.model.entity;

import com.ewide.core.orm.annotation.Column;
import com.ewide.core.orm.annotation.Id;
import com.ewide.core.orm.annotation.Table;

import java.io.Serializable;

/**
 * 步行
 * Created by Taoze on 2018/5/31.
 */

@Table(name = "Walk")
public class Walk implements Serializable {

    /**
     * 步数
     */
    @Id
    @Column
    private String id;
    @Column
    private int count;
    @Column
    private long lastTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
