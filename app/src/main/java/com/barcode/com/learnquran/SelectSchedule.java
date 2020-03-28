package com.barcode.com.learnquran;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectSchedule extends AppCompatActivity {
    TextView tvtop1eng,tvtop2eng,tvtop1arb,tvtop2arb,tvmid1eng,tvmid2eng,tvmid1arb,tvmid2arb;
    ImageView gotosch1,gotosch2,chnglangeng,chnglangarb;
    String lang,schedule;
    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_schedule);

        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        lang= sharedpreferences.getString("lang",null);
        schedule=sharedpreferences.getString("schedule",null);
       // Toast.makeText(this,""+lang,Toast.LENGTH_LONG).show();

        tvtop1eng = (TextView) findViewById(R.id.top1eng);
        tvtop2eng = (TextView) findViewById(R.id.top2eng);
        tvtop1arb = (TextView) findViewById(R.id.top1arb);
        tvtop2arb = (TextView) findViewById(R.id.top2arb);
        tvmid1eng = (TextView) findViewById(R.id.mid1eng);
        tvmid2eng = (TextView) findViewById(R.id.mid2eng);
        tvmid1arb = (TextView) findViewById(R.id.mid1arb);
        tvmid2arb = (TextView) findViewById(R.id.mid2arb);
        gotosch1 = (ImageView) findViewById(R.id.gotosch1);
        gotosch2 = (ImageView) findViewById(R.id.gotosch2);
        chnglangarb =(ImageView) findViewById(R.id.chnglangarb);
        chnglangeng=(ImageView) findViewById(R.id.chnglangeng);
       // Intent iin= getIntent();
       // Bundle b = iin.getExtras();
       // if(b!=null)
        //{
         //   lang =(String) b.get("lang");
            if(lang!=null){
              //  Toast.makeText(this,""+lang,Toast.LENGTH_LONG).show();
                if(lang.equals("eng")){
                    tvtop1eng.setVisibility(View.VISIBLE);
                    tvtop2eng.setVisibility(View.VISIBLE);
                    tvmid1eng.setVisibility(View.VISIBLE);
                    tvmid2eng.setVisibility(View.VISIBLE);

                //    Toast.makeText(this,"in enlgish",Toast.LENGTH_LONG).show();
                }else if(lang.equals("arb")){
                    tvtop1arb.setVisibility(View.VISIBLE);
                    tvtop2arb.setVisibility(View.VISIBLE);
                    tvmid1arb.setVisibility(View.VISIBLE);
                    tvmid2arb.setVisibility(View.VISIBLE);

                  //  Toast.makeText(this,"in arabic",Toast.LENGTH_LONG).show();
                }
                initCustomSpinner();

            }
        gotosch1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SelectSchedule.this, ThirtyDayEng.class);
                // myIntent.putExtra("forward",true);

                SelectSchedule.this.startActivity(myIntent);
                finish();
            }
        });
        gotosch2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SelectSchedule.this, ThirtyDayEng.class);
                // myIntent.putExtra("forward",true);

                SelectSchedule.this.startActivity(myIntent);
                finish();
            }
        });
           // Textv.setText(j);
        chnglangarb.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                editor = sharedpreferences.edit();
                editor.putString("lang","");
                editor.commit();
                Intent myIntent = new Intent(SelectSchedule.this, MainActivity.class);
                // myIntent.putExtra("lang","arb");

                SelectSchedule.this.startActivity(myIntent);
                finish();
            }
        });
        chnglangeng.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                editor = sharedpreferences.edit();
                editor.putString("lang","");
                editor.commit();
                Intent myIntent = new Intent(SelectSchedule.this, MainActivity.class);
                // myIntent.putExtra("lang","arb");

                SelectSchedule.this.startActivity(myIntent);
                finish();
            }
        });
      //  }
        //Android Custom Spinner Example Programmatically
      //  getMenuInflater().inflate(R.menu.my_context_menu, menu);

        //String title = "item three";
        //int groupId = Menu.NONE;
        //int itemId = MENU_ITEM;
        //int order = 103;
        //menu.add(groupId, itemId, order, title);
        //return true;
    }

    private void initCustomSpinner() {
        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        Spinner spinnerCustom= (Spinner) findViewById(R.id.spinnerCustom);
        // Spinner Drop down elements
        ArrayList<String> languages = new ArrayList<String>();
        if(lang.equals("eng")) {

            languages.add("Select a schedule");
            languages.add("30 Weeks\n" +
                    "(One Chapter every week)");
            languages.add("60 Weeks\n" +
                    "(One Hezib every week)");
            languages.add("120 Weeks\n" +
                    "(1/2 Hezib every week)");
            languages.add("240 Weeks\n" +
                    "(1/4 Hezib every week)");
            languages.add("Two Years\n" +
                    "(Daily Schedule with Revision)");
        }else if(lang.equals("arb")){

            languages.add("حدد جدولا زمنيا");
            languages.add("30 أسبوع\n" +
                    "(جزء واحد كل أسبوع)");
            languages.add("60 أسبوع\n" +
                    "(حزب واحد كل أسبوع)");
            languages.add("120 أسبوع\n" +
                    "(نصف حزب كل أسبوع)");
            languages.add("240 أسبوع\n" +
                    "(ربع حزب كل أسبوع)");
            languages.add("سنتان\n" +
                    "(برنامج يومي مع المراجعة)");
        }
        gotosch1 = (ImageView) findViewById(R.id.gotosch1);
        gotosch2 = (ImageView) findViewById(R.id.gotosch2);
        chnglangarb =(ImageView) findViewById(R.id.chnglangarb);
        chnglangeng=(ImageView) findViewById(R.id.chnglangeng);
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(SelectSchedule.this,languages);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        if(schedule!=null){
            if(schedule.equals("30")){
                //Toast.makeText(this,"Already Select",Toast.LENGTH_LONG).show();
                spinnerCustom.setSelection(1);
                spinnerCustom.setEnabled(false);
            }
            if(lang.equals("eng")) {
                gotosch2.setVisibility(View.VISIBLE);
                chnglangeng.setVisibility(View.VISIBLE);
            }
            else if(lang.equals("arb")) {
                gotosch1.setVisibility(View.VISIBLE);
                chnglangarb.setVisibility(View.VISIBLE);
            }
        }
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                //Toast.makeText(parent.getContext(), position+ " " + item, Toast.LENGTH_LONG).show();

                switch (position){
                    case 0:
                        editor.putString("schedule",null);
                        editor.commit();
                        break;
                    case 1:
                        Intent iin= getIntent();
                        Bundle b = iin.getExtras();
                        Boolean forward = false;
                        if(b!=null)
                            forward = b.getBoolean("forward");
                        //Toast.makeText(parent.getContext(), "30 days", Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(SelectSchedule.this, ThirtyDayEng.class);
                        if(lang.equals("eng")) {
                           // myIntent.putExtra("lang", "eng");
                            //myIntent.putExtra("Heading", item);
                            editor.putString("schedule","30");
                            editor.commit();
                        }else if(lang.equals("arb")) {
                          //  myIntent.putExtra("lang", "arb");
                           // myIntent.putExtra("Heading", item);
                            editor.putString("schedule","30");
                            editor.commit();
                        }
                        Spinner spinnerCustom= (Spinner) findViewById(R.id.spinnerCustom);
                        spinnerCustom.setEnabled(false);
                        if(lang.equals("arb")) {
                            findViewById(R.id.gotosch1).setVisibility(View.VISIBLE);
                            findViewById(R.id.chnglangarb).setVisibility(View.VISIBLE);
                        }
                        if(lang.equals("eng")) {
                            findViewById(R.id.gotosch2).setVisibility(View.VISIBLE);
                            findViewById(R.id.chnglangeng).setVisibility(View.VISIBLE);
                        }
                        if(!forward) {
                            SelectSchedule.this.startActivity(myIntent);
                            finish();
                        }
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Schedule Comming Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "Schedule Comming Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(parent.getContext(), "Schedule Comming Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(parent.getContext(), "Schedule Comming Soon", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.chngsche:
               // Toast.makeText(this,"Chnage schedule",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Confirm");
                builder.setMessage("Your previous schedule will be reset, are you sure ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        Spinner spinnerCustom= (Spinner) findViewById(R.id.spinnerCustom);
                        spinnerCustom.setEnabled(true);
                        spinnerCustom.setSelection(0);
                        findViewById(R.id.gotosch1).setVisibility(View.INVISIBLE);
                        findViewById(R.id.gotosch2).setVisibility(View.INVISIBLE);
                        dialog.dismiss();
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
            case R.id.chnglang:
                //Toast.makeText(this,"Chnage Language",Toast.LENGTH_LONG).show();
                sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                editor = sharedpreferences.edit();
                editor.putString("lang","");
                editor.commit();
                Intent myIntent = new Intent(SelectSchedule.this, MainActivity.class);
                // myIntent.putExtra("lang","arb");

                SelectSchedule.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(SelectSchedule.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            txt.setBackgroundResource(R.drawable.bottom_line);
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(SelectSchedule.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_down, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));

            return  txt;
        }

    }


}
