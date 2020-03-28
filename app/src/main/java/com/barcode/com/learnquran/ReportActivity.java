package com.barcode.com.learnquran;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    String lang;
    DBhandler myDB = new DBhandler(ReportActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        lang= sharedpreferences.getString("lang",null);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        pieChart = (PieChart) findViewById(R.id.chart1);
        pieChart.setUsePercentValues(true);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(new int[]{R.color.chartGreen , R.color.chartBlue},this);

        pieChart.setData(pieData);

        pieChart.animateY(3000);

    }
    public void AddValuesToPIEENTRY(){
            String column ="";
            if(lang.equals("eng"))
                column = myDB.KEY_WEEK_ENG;
            else
                column = myDB.KEY_WEEK_ARB;

        try {

            float comp = myDB.getReadCount(column);
            float uncom = myDB.getUnReadCount(column);
            float total = 30;

            comp = (comp/total)*100;
            uncom = (uncom/total)*100;
            entries.add(new BarEntry(comp, 0));
            entries.add(new BarEntry(uncom, 1));
        }catch (Exception e){
            Toast.makeText(this,""+e,Toast.LENGTH_LONG).show();
        }

    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("% Completed");
        PieEntryLabels.add("% Remaining");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.resetsch:

                return true;
            case R.id.report:

                return true;
            case android.R.id.home:
                Intent myIntent2 = new Intent(ReportActivity.this, ThirtyDayEng.class);
               // myIntent2.putExtra("forward",true);

                ReportActivity.this.startActivity(myIntent2);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
