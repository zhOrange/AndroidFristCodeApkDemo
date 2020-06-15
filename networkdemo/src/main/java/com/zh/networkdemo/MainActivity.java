package com.zh.networkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView responseContent;
    EditText urlText;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseContent = (TextView)findViewById(R.id.contentText);
        urlText = (EditText)findViewById(R.id.urlText);
        btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendRequestWithHttpURLConnection();
                //sendRequestWithOKHttp();
                HttpUtil.sendOKHttpRequest("https://www.baidu.com", new okhttp3.Callback(){

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseData = response.body().string();
                        showResponse(responseData);
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(reader != null){
                        try{
                            reader.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void  sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr = urlText.getText().toString();
                if("".equals(urlStr)){
                    return;
                }

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
//                            .url("https://"+urlStr)
                            .url("http://10.0.2.2/getXml.xml")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d(TAG, "run: " + responseData);
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseContent.setText(response);
            }
        });
    }
}
