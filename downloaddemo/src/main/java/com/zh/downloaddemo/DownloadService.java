package com.zh.downloaddemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.File;

public class DownloadService extends Service {

    class DownloadBinder extends Binder{
        public void startDownload(String url){
            downloadUrl = url;
            downloadTask = new DownloadTask(downloadListener);
            downloadTask.execute(downloadUrl);
            Notification notification = getNotification("Downloading...", 0);
            startForeground(1, notification);
            Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
        }
        public void pauseDownload(){
            if(downloadTask != null){
                downloadTask.pausedDownload();
            }
        }
        public void canceledDownload(){
            if(downloadTask != null){
                downloadTask.cancelDownload();
            }
            if(downloadUrl !=null){
                String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory+fileName);
                if(file.exists()){
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();

            }
        }

    }

    private DownloadTask downloadTask;
    private String downloadUrl;

    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading Success", -1));
            Toast.makeText(DownloadService.this, "Download Success.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Download Paused.", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Download Canceled.", Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }
    private NotificationManager getNotificationManager(){
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress){
        Intent nfIntent = new Intent(DownloadService.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, nfIntent, 0);
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

//高版本需要渠道
        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            //只在Android O之上需要渠道，这里的第一个参数要和下面的channelId一样
            NotificationChannel notificationChannel = new NotificationChannel("1","name",NotificationManager.IMPORTANCE_HIGH);
            //如果这里用IMPORTANCE_NOENE就需要在系统的设置里面开启渠道，通知才能正常弹出
            manager.createNotificationChannel(notificationChannel);
        }
//这里的第二个参数要和上面的第一个参数一样
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        if(progress >= 0){
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        // 获取构建好的Notification
        Notification notification = builder.build();
        return notification;
    }
}
