package com.taoze.weather.model;

/**
 * Model层接口定义基类
 * Created by Taoze on 2018/6/11.
 */

public interface IBaseModel {

    /**
     * 用于处理Model销毁操作, 释放资源, 避免oom
     */
    public void onModelDestroy();
}
