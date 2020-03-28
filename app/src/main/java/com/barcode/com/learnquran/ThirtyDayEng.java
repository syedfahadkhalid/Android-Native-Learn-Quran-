package com.barcode.com.learnquran;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ThirtyDayEng extends AppCompatActivity implements AdapterView.OnItemClickListener  {
    String lang,schedule;
    TextView top1eng,id,title,week,from,to;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    ListView listView;
    DBhandler myDB = new DBhandler(ThirtyDayEng.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty_day_eng);
        top1eng = (TextView) findViewById(R.id.top1eng);
        id = (TextView)findViewById(R.id.TVId) ;
        title = (TextView)findViewById(R.id.TVTitle);
        week = (TextView)findViewById(R.id.TVWeek);
        from =(TextView)findViewById(R.id.TVFrom);
        to = (TextView)findViewById(R.id.TVTo);






        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        lang= sharedpreferences.getString("lang",null);
        schedule = sharedpreferences.getString("schedule",null);
        setHeading(schedule);
        //Intent iin= getIntent();
      //  Bundle b = iin.getExtras();
        //if(b!=null)
        //{
           // lang =(String) b.get("lang");
          //  heading = (String) b.get("Heading");
            //top1eng.setText(heading);
        //}

        DBhandler db = new DBhandler(this);

        listView = (ListView) findViewById(R.id.listView1);
        //TextView textView = new TextView(ThirtyDayEng.this);
        //textView.setText("Select what you want to cook");

        //listView.addHeaderView(textView);
        listView.setOnItemClickListener(this);

        try {
            populateListView();
        }catch (Exception e){
            Toast.makeText(this,""+e, Toast.LENGTH_LONG).show();
            Log.d("Error: ", ""+e);
        }

        if(lang.equals("arb")){
            id.setText("رقم");
            id.setGravity(Gravity.LEFT);
            title.setText("عنوان");
            week.setText("أسبوع");
            from.setText("من عند");
            from.setGravity(Gravity.LEFT);
            to.setText("الى");

        }
    }

    public void setHeading(String schedule){
        top1eng = (TextView) findViewById(R.id.top1eng);
        switch (schedule){
            case "30":
                if(lang.equals("eng")){
                    top1eng.setText("30 Weeks\n(One Chapter every week)");
                }else if(lang.equals("arb")){
                    top1eng.setText("30 أسبوع\n" +
                            "(جزء واحد كل أسبوع)");
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        TextView tv = (TextView) v.findViewById(R.id.textViewStatus);
        String status = tv.getText().toString();
        int st=0;
        if(status.equals("0"))
            st=1;
        else if(status.equals("1"))
            st=0;
      //  Toast.makeText(this,"You clicked Item: " + id + " at position:" + position+" status: "+status, Toast.LENGTH_LONG).show();
        myDB.updateStatus(id,st);
        listView = (ListView) findViewById(R.id.listView1);
        Intent myIntent = new Intent( ThirtyDayEng.this,ThirtyDayEng.class);
        //myIntent.putExtra("FoodId",id);
        ThirtyDayEng.this.startActivity(myIntent);
        ThirtyDayEng.this.finish();
        populateListView();
    }
    public void populateListView() {

        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        lang= sharedpreferences.getString("lang",null);

        Cursor cursor = myDB.getAllRows();
        String column = "";
        String week = "";
        int lay = R.layout.listitem2;
        if (lang.equals("eng")) {
            column = DBhandler.KEY_ENG;
            week = DBhandler.KEY_WEEK_ENG;
            lay = R.layout.listitem;
        } else if (lang.equals("arb")) {
            column = DBhandler.KEY_ARB;
            week = DBhandler.KEY_WEEK_ARB;

        }

        String[] fromFieldNames = new String[]{DBhandler.KEY_ID, column, week, DBhandler.KEY_FROM, DBhandler.KEY_TO,DBhandler.KEY_STATUS};

        int[] toViewIDs = new int[]{R.id.TextViewId, R.id.TextViewTitle, R.id.textViewWeek, R.id.textViewFrom, R.id.textViewTo,R.id.textViewStatus};

        SimpleCursorAdapter myCusrsorAdapter;

        myCusrsorAdapter = new SimpleCursorAdapter(getBaseContext(),lay, cursor, fromFieldNames, toViewIDs, 0);


        myCusrsorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int column) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.listitem, null);

                if(lang.equals("eng")) {
                    if (column == 4) {
                        TextView tv = (TextView) view;
                        String mPltc = cursor.getString(cursor.getColumnIndex("status"));
                        //if (BuildConfig.DEBUG) {
                        Log.d("hey", "loadAllianceData(): Alliance Name: " + cursor.getString(cursor.getColumnIndex("status")));
                        Log.d("no", "loadAllianceData(): Political Relation: " + mPltc);
                        //}

                        // Set color of item based on Political Relation
                        if (mPltc.equals("1")) {
                            //view.setBackgroundColor(Color.rgb(34,117,76));
                            // rowView.setVisibility(View.INVISIBLE);
                            String mAN = cursor.getString(cursor.getColumnIndex("week"));
                            tv.setText(mAN);
                            View newView = (View) view.getParent();
                            newView.setBackgroundColor(Color.rgb(83, 181, 73));

                        }
                        if (mPltc.equals("0")) {


                            String mAN = cursor.getString(cursor.getColumnIndex("week"));
                            View newView = (View) view.getParent();
                            newView.setBackgroundColor(Color.rgb(255, 255, 255));
                            tv.setText(mAN);
                        }


                        return true;
                    }
                }else if(lang.equals("arb")){
                    if (column == 6) {
                        TextView tv2 = (TextView) view;
                        String mPltc = cursor.getString(cursor.getColumnIndex("status"));
                        //if (BuildConfig.DEBUG) {
                        Log.d("hey", "loadAllianceData(): Alliance Name: " + cursor.getString(cursor.getColumnIndex("status")));
                        Log.d("no", "loadAllianceData(): Political Relation: " + mPltc);
                        //}

                        // Set color of item based on Political Relation
                        if (mPltc.equals("1")) {
                            //view.setBackgroundColor(Color.rgb(34,117,76));
                            // rowView.setVisibility(View.INVISIBLE);
                            String mAN = cursor.getString(cursor.getColumnIndex("end"));
                            tv2.setText(mAN);
                            View newView = (View) view.getParent();
                            newView.setBackgroundColor(Color.rgb(83, 181, 73));

                        }
                        if (mPltc.equals("0")) {


                            String mAN = cursor.getString(cursor.getColumnIndex("end"));
                            View newView = (View) view.getParent();
                            newView.setBackgroundColor(Color.rgb(255, 255, 255));
                            tv2.setText(mAN);
                        }


                        return true;
                    }
                }
                return false;
            }
        });


        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(myCusrsorAdapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_context_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.resetsch:
                // Toast.makeText(this,"Reset Schedule",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Confirm");
                builder.setMessage("Are sure you want to rest your schedule ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        myDB.restStatus();
                        Intent myIntent = new Intent( ThirtyDayEng.this,ThirtyDayEng.class);
                        //myIntent.putExtra("FoodId",id);
                        ThirtyDayEng.this.startActivity(myIntent);
                        finish();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.report:
                //Toast.makeText(this,"Reports",Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent( ThirtyDayEng.this,ReportActivity.class);
                //myIntent.putExtra("FoodId",id);
                ThirtyDayEng.this.startActivity(myIntent);
                finish();
                return true;
            case android.R.id.home:
                Intent myIntent2 = new Intent(ThirtyDayEng.this, SelectSchedule.class);
                myIntent2.putExtra("forward",true);

                ThirtyDayEng.this.startActivity(myIntent2);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
