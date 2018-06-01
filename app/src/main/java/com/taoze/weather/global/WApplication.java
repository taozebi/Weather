package com.taoze.weather.global;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

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
    }
}
