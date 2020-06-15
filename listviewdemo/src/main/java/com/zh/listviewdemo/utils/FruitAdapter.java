package com.zh.listviewdemo.utils;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zh.listviewdemo.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resId;
    public FruitAdapter(Context context, int textViewResId, List<Fruit> objects){
        super(context, textViewResId, objects);
        resId = textViewResId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resId, parent, false);
            viewHolder = new ViewHolder((ImageView)view.findViewById(R.id.fruit_image), (TextView)view.findViewById(R.id.fruit_name));
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder =(ViewHolder)view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
        ViewHolder(ImageView image, TextView name){
            this.fruitImage = image;
            this.fruitName = name;
        }
    }

}
