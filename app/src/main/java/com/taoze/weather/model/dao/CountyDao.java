package com.taoze.weather.model.dao;

import android.content.Context;

import com.ewide.core.orm.dao.DBDao;
import com.taoze.weather.model.entity.County;

/**
 * Created by Taoze on 2018/6/1.
 */

public class CountyDao extends DBDao<County>{
    public CountyDao(Context context) {
        super(context);
    }
}
