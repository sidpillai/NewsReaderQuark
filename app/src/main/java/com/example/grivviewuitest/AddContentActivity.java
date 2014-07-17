package com.example.grivviewuitest;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.grivviewuitest.R;

public class AddContentActivity extends Activity  {

    private static Spinner spinnercat;
    private static Button btnSubmit1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
//        spinnercat = (Spinner) findViewById(R.id.spinner);
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.categories, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinnercat.setAdapter(adapter);


        addListenerOnButton();

//        addListenerOnSpinnerItemSelection();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_content, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_add_content, container, false);

            spinnercat = (Spinner) rootView.findViewById(R.id.spinner);
            spinnercat.setOnItemSelectedListener(new CustomOnItemSelListen());

            btnSubmit1 = (Button) rootView.findViewById(R.id.addcat);

            btnSubmit1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(),
                            "OnClickListener : " +
                                    "\nSpinner 1 : "+ String.valueOf(spinnercat.getSelectedItem()) ,
                            Toast.LENGTH_SHORT).show();

                }
            });
            return rootView;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    public void addListenerOnButton() {



    }


}
