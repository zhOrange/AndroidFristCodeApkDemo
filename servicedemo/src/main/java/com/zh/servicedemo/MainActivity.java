package com.zh.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    Button btnStop;
    Button btnBind;
    Button btnUnbind;
    Button btnStartIntentService;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.DownloadBinder binder;
            binder = (MyService.DownloadBinder) service;
            binder.startDownload();
            binder.getProcess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btn_start);
        btnStop = (Button)findViewById(R.id.btn_stop);
        btnBind = (Button)findViewById(R.id.btn_bind_service);
        btnUnbind = (Button)findViewById(R.id.btn_unbind_service);
        btnStartIntentService = (Button)findViewById(R.id.btn_intent_service);
        btnStartIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentservice = new Intent(MainActivity.this, MyIntentService.class);
                startService(intentservice);
            }
        });



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
//                startService(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //android8.0以上通过startForegroundService启动service
                    startForegroundService(intent);
                } else {
                    startService(intent);
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
    }
}
