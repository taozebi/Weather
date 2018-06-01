package com.taoze.weather.model.entity;

import com.ewide.core.orm.annotation.BusinessId;
import com.ewide.core.orm.annotation.Column;
import com.ewide.core.orm.annotation.Table;

/**
 * 市级行政区
 * Created by Taoze on 2018/6/1.
 */
@Table(name = "City")
public class City {
    /**
     * 市ID
     */
    @BusinessId
    @Column
    public String id;
    /**
     * 市名称
     */
    @Column
    public String name;
    /**
     * 省名称
     */
    @Column
    public String pid;

}
