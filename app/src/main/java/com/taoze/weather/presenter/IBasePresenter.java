package com.taoze.weather.presenter;

/**
 * Presenter层接口定义基类
 * Created by Taoze on 2018/6/11.
 */

public interface IBasePresenter {

    /**
     * 用于Presenter处理销毁事件
     */
    public void onPresenterDestroy();
}
