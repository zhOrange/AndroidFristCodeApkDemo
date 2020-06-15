package com.zh.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zh.listviewdemo.utils.Fruit;
import com.zh.listviewdemo.utils.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fruit> fruits = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
//        String[] data = {"1", "2", "3", "4", "5", "6","1", "2", "3", "4", "5", "6","1", "2", "3", "4", "5", "6","1", "2", "3", "4", "5", "6","1", "2", "3", "4", "5", "6","1", "2", "3", "4", "5", "6"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruits);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruits.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits(){
        for(int  i = 0; i < 5; i++){
            fruits.add(new Fruit("1", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("2", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("3", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("4", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("5", R.drawable.ic_launcher_foreground));
            fruits.add(new Fruit("6", R.drawable.ic_launcher_foreground));
        }
    }
}
