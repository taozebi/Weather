package com.taoze.weather.model;

/**
 * Walk模型接口定义
 * Created by Taoze on 2018/6/1.
 */

public interface IWalkModel extends IBaseModel{

    /**
     * 获取步数
     * @return 步数
     */
    public int getWalkCount();

    /**
     * 设置步数
     * @param count 步数
     */
    public void setWalkCount(int count);
}
