package com.barcode.com.learnquran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imgeng,imgarb;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    String lang="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        lang= sharedpreferences.getString("lang",null);
        if(lang!=null) {
            if (lang.equals("eng"))
                IntentActivityEng();
            else if (lang.equals("arb"))
                IntentActivityArb();
        }

        imgeng = (ImageView) findViewById(R.id.imageView4);
        imgarb = (ImageView) findViewById(R.id.imageView);
        imgeng.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivityEng();


            }
        });
        imgarb.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivityArb();

            }
        });

    }

    public void IntentActivityEng()
    {
        editor.putString("lang","eng");
        editor.commit();
        Intent myIntent = new Intent(MainActivity.this, SelectSchedule.class);
        //myIntent.putExtra("lang","eng");

        MainActivity.this.startActivity(myIntent);
        finish();
    }

    public void IntentActivityArb()
    {
        editor.putString("lang","arb");
        editor.commit();
        Intent myIntent = new Intent(MainActivity.this, SelectSchedule.class);
        // myIntent.putExtra("lang","arb");

        MainActivity.this.startActivity(myIntent);
        finish();
    }

}
