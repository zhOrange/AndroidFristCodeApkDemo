package com.zh.cahtuidemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button btnSend;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText)findViewById(R.id.edit_msg);
        btnSend = (Button)findViewById(R.id.btn_send);
        msgRecyclerView = (RecyclerView)findViewById(R.id.msg_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(manager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    int type;
                    if(content.startsWith(".")){
                        type = Msg.TYPE_RECEIVED;
                    }
                    else{
                        type = Msg.TYPE_SENT;
                    }
                    Msg msg = new Msg(content,type);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");
                }
            }
        });

    }
}
