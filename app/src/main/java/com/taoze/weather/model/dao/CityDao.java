package com.taoze.weather.model.dao;

import android.content.Context;

import com.ewide.core.orm.dao.DBDao;
import com.taoze.weather.model.entity.City;

/**
 * Created by Taoze on 2018/6/1.
 */

public class CityDao extends DBDao<City>{
    public CityDao(Context context) {
        super(context);
    }
}
