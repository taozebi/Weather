package com.taoze.weather.ui.view;

/**
 * View接口定义基类
 * Created by Taoze on 2018/6/11.
 */

public interface IBaseView {
    /**
     * 用户处理View销毁的操作, 释放资源, 避免OOM
     */
    public void onViewDestory();
}
