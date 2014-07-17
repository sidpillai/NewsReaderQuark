package com.example.grivviewuitest;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by 0000101799 on 2014/07/17.
 */
public class CustomOnItemSelListen implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
