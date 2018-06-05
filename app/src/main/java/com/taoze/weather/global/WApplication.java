package com.taoze.weather.global;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.ewide.core.orm.DBConfig;
import com.taoze.weather.R;
import com.taoze.weather.util.Utils;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by Taoze on 2018/5/31.
 */

public class WApplication extends Application{

    public static final String TAG = "weather";

    private static WApplication instance;

    private List<Activity> activities = new ArrayList<Activity>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activities.contains(activity)) {
            activities.remove(activity);
        }
    }

    public Activity getTopActivity(){
        return activities.get(activities.size()-1);
    }

    public static WApplication getInstance(){
        if(instance != null){
            return instance;
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDB();
        initializeOkHttp();
    }

    private void initializeDB() {
        Log.d(WApplication.TAG, "init DB");
        String path = Utils.getAppDir(this, getResources().getString(R.string.app_name)) + "/config/" + "db";
        Log.d(WApplication.TAG, "Database Path --> " + path);
        DBConfig.DB_PATH = path;
        DBConfig.DB_VERSION = 3;
    }

    private void initializeOkHttp() {
        Log.d(TAG,"init OKHttp");
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        builder.setDebug(false);
        OkHttpFinal.getInstance().init(builder.build());
    }
}
