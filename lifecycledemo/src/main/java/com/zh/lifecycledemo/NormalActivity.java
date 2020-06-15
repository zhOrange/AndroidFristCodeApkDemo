package com.zh.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NormalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}
