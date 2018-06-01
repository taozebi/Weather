package com.taoze.weather.presenter;

/**
 * 步行管理接口定义
 * Created by Taoze on 2018/5/31.
 */

public interface IWalkPresenter {
    public void loadWalk(String cityName);

    public void saveWalkCount(int count);
}
