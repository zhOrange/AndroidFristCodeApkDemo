package com.zh.lifecycledemo;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController {
    private static List<Activity> activityList = new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        if(!activityList.isEmpty()){
            activityList.remove(activity);
        }
    }

    /**
     *清空所有活动
     */
    public static void finishAll(){
        for(Activity activity: activityList){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activityList.clear();
    }
}
