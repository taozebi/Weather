package com.taoze.weather.model.entity;

import com.ewide.core.orm.annotation.BusinessId;
import com.ewide.core.orm.annotation.Column;
import com.ewide.core.orm.annotation.Table;

/**
 * 省份
 * Created by Taoze on 2018/6/1.
 */
@Table(name = "Province")
public class Province {
    /**
     * 省ID
     */
    @BusinessId
    @Column
    public String id;
    /**
     * 省名称
     */
    @Column
    public String name;
}
