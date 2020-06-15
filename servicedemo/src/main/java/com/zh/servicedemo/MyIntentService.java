package com.zh.servicedemo;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {
    public MyIntentService(){
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
