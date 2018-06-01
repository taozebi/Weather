package com.taoze.weather.model.entity;

import com.ewide.core.orm.annotation.BusinessId;
import com.ewide.core.orm.annotation.Column;
import com.ewide.core.orm.annotation.Table;

/**
 * 县级行政区(包含天气代码code)
 * Created by Taoze on 2018/6/1.
 */
@Table(name = "County")
public class County {
    /**
     * 县ID
     */
    @BusinessId
    @Column
    public String id;
    /**
     * 县名称
     */
    @Column
    public String name;
    /**
     * 天气查询代码
     */
    @Column
    public String code;
    /**
     * 市ID
     */
    @Column
    public String cid;
    /**
     * 省ID
     */
    @Column
    public String pid;
}
