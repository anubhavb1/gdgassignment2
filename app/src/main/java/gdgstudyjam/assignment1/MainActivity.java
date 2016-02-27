package gdgstudyjam.assignment1;

import android.app.ListActivity;
import android.os.Bundle;

//package com.example.android.gdg_cust_listview;

public class MainActivity extends ListActivity {

    String[] rappers = {
            "Eminem",
            "Dr. Dre",
            "Ice Cube",
            "Eazy e",
            "2pac"
    };
    String[] descb={
            "The Rap God",
            "The Gangster",
            "The man with the words",
            "The blessed one",
            "The swag man"
    };
    Integer[] imageIDs = {
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdvancedCustomArrayAdapter adapter = new AdvancedCustomArrayAdapter(this,rappers,imageIDs,descb);
        setListAdapter(adapter);

    }



}
