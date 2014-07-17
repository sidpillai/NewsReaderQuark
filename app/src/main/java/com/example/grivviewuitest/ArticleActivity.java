package com.example.grivviewuitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.grivviewuitest.adapter.ImageAdapter;

import java.util.ArrayList;

public class ArticleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);


        ArrayList<String> list = new ArrayList<String>();
//        list.add("title 1");
//        list.add("title 2");
//        list.add("title 3");
//        list.add("title 1");
//        list.add("title 1");
//        list.add("title 4");
//        list.add("title 1");
//        list.add("titlebxc 1");
//        list.add("title 1b");
//        list.add("title cvb1");
//        list.add("title 1");
//        list.add("title 2");
//        list.add("title 3");
//        list.add("title 1");
//        list.add("title 1");
//        list.add("title 4");
//        list.add("title 1");
//        list.add("titlebxc 1");
//        list.add("title 1b");
//        list.add("title cvb1");

        list= MainActivity.list;
        //Get intent data
        Intent intent = getIntent();

        //selected image id
        int position = intent.getExtras().getInt("id");
        final ImageAdapter imageAdapter = new ImageAdapter(this, list);
      //  ImageView imageView = (ImageView) findViewById(R.id.SingleView);
      //  imageView.setImageResource(imageAdapter.mThumbIds[position]);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, list));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ArticleActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("id",position);
             //   ImageView imageView = (ImageView) findViewById(R.id.SingleView);
               // imageView.setImageResource(imageAdapter.mThumbIds[position]);
                //setContentView(R.layout.activity_article);

            }
        });

//        gridview = (GridView) findViewById(R.id.gridview);
//        //gridview.setAdapter(new ImageAdapter(this, list));
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(),ArticleActivity.class);
//                intent.putExtra("id",position);
//                startActivity(intent);
//
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
