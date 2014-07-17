package com.example.grivviewuitest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grivviewuitest.MainActivity;
import com.example.grivviewuitest.R;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by 0000101799 on 2014/07/07.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    ArrayList<String> title;
    public ImageAdapter(Context c, ArrayList<String> a) {
        mContext = c;
        title = a;

    }

    public int getCount() {
        return title.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(500,300));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
     // title = MainActivity.list;
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
           // grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_sample, null);
            grid.setLayoutParams(new GridView.LayoutParams(500,300));
            TextView textView = (TextView) grid.findViewById(R.id.txt);
            ImageView imageView = (ImageView)grid.findViewById(R.id.img);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            textView.setText(title.get(position));
            imageView.setImageResource(mThumbIds[getRandom()]);
        } else {
            grid = convertView;
        }
        return grid;
    }

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };

    int getRandom(){
        int min = 0;
        int max = 10;

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return i1;
    }
}

