package com.zh.downloaddemo;

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPause();
    void onCanceled();
}
