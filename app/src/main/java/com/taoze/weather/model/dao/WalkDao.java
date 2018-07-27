package com.taoze.weather.model.dao;

import android.content.Context;

import com.ewide.core.orm.dao.DBDao;
import com.taoze.weather.model.entity.Walk;

/**
 * Created by Taoze on 2018/7/27.
 */

public class WalkDao extends DBDao<Walk>{
    public WalkDao(Context context) {
        super(context);
    }

    public void insert(Walk walk){

    }
}
