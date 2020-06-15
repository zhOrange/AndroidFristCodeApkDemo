package com.zh.downloaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDownload;
    Button btnPause;
    Button btnCancel;

    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button)findViewById(R.id.btn_download);
        btnPause = (Button)findViewById(R.id.btn_pause);
        btnCancel = (Button)findViewById(R.id.btn_cancel);
        btnDownload.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        Intent intent = new Intent(MainActivity.this, DownloadService.class);
        if(Build.VERSION.SDK_INT>=26){
            startForegroundService (intent);
        }else{
            startService (intent);
        }
        bindService(intent, connection, BIND_AUTO_CREATE);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "无读写权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    @Override
    public void onClick(View v) {
        if(downloadBinder ==null){
            return;
        }
        switch (v.getId()){
            case R.id.btn_download:
                String url = "https://dldir1.qq.com/weixin/Windows/WeChatSetup.exe";
                downloadBinder.startDownload(url);
                break;
            case R.id.btn_pause:
                downloadBinder.pauseDownload();
                break;
            case R.id.btn_cancel:
                downloadBinder.canceledDownload();
                break;
            default:
                break;
        }
    }
}
