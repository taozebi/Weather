package com.taoze.weather.model.dao;

import android.content.Context;

import com.ewide.core.orm.dao.DBDao;
import com.taoze.weather.model.entity.Province;

/**
 * Created by Taoze on 2018/6/1.
 */

public class ProvinceDao extends DBDao<Province>{
    public ProvinceDao(Context context) {
        super(context);
    }
}
