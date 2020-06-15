package com.zh.playvideodemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final String[] REQUESTPERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int WRITE_REQUEST_CODE = 0;

    private String sourcePath;

    @BindView(R.id.btn_play)
    Button btnPlay;
    @BindView(R.id.btn_pause)
    Button btnPause;
    @BindView(R.id.btn_replay)
    Button btnStop;

    @BindView(R.id.videoView)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        sourcePath = Environment.getExternalStorageDirectory() + "/Movies";
        Log.d(TAG, "onCreate: the source path is: " + sourcePath);

        verifyPermission();
    }

    private void initMediaPlayer(){
        try{
            File file = new File(sourcePath, "movie.mp4");
            videoView.setVideoPath(file.getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void verifyPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, REQUESTPERMISSIONS, WRITE_REQUEST_CODE);
        }
        else{
            initMediaPlayer();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMediaPlayer();
                }else{
                    Toast.makeText(MainActivity.this, "拒绝授权，程序退出", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                if(!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.btn_pause:
                if(videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.btn_replay:
                if(videoView.isPlaying()){
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();
        }
    }
}
