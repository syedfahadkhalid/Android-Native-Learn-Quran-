package com.barcode.com.learnquran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "LearQuranDB";
    // Contacts table name
    private static final String TABLE_SCHEDULE_30= "schedule30";
    private static final String TABLE_SCHEDULE_60= "schedule60";
    private static final String TABLE_SCHEDULE_120= "schedule120";
    private static final String TABLE_SCHEDULE_240= "schedule240";
    private static final String TABLE_SCHEDULE_2= "schedule2";

    // Shops Table Columns names
    public static final String KEY_ID = "AyatId";
    public static final String KEY_ENG = "eng";
    public static final String KEY_ARB = "arb";
    public static final String KEY_WEEK_ENG = "week";
    public static final String KEY_WEEK_ARB = "week2";
    public static final String KEY_FROM = "strt";
    public static final String KEY_TO = "end";
    public static final String KEY_STATUS = "status";




    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SCHEDULE_30 + "("
                + KEY_ID + " INTEGER PRIMARY KEY   AUTOINCREMENT, " + KEY_ENG + " TEXT, "
                + KEY_ARB + " TEXT, "+ KEY_WEEK_ENG+ " TEXT, "+ KEY_FROM + " INTEGER, "+ KEY_TO + " INTEGER, " +KEY_STATUS+ " INTEGER, "+KEY_WEEK_ARB+" TEXT);";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL("INSERT INTO "+ TABLE_SCHEDULE_30 +" (eng,arb,week,strt,end,status,week2) " +
                "VALUES " +
                "('Al-Fatiha','الفاتحة','Week 1 - Chapter 1',1,7,0,'الأسبوع 1 - الجزء 1')," +
                "('Al-Baqarah','البقرة','Week 1 - Chapter 1',1,141,0,'الأسبوع 1 - الجزء 1')," +
                "('Al-Baqarah','البقرة','Week 2 - Chapter 2',142,252,0,'الأسبوع 2 - الجزء 2')," +
                "('Al-Baqarah','البقرة','Week 3 - Chapter 3',253,286,0,'الأسبوع 3 - الجزء 3')," +
                "('Al-i-Imran','آل عمران','Week 3 - Chapter 3',1,92,0,'3 لأسبوع 3 - الجزء')," +
                "('Al-i-Imran','آل عمران','Week 4 - Chapter 4',93,200,0,'الأسبوع 4 - الجزء 4')," +
                "('An-Nisaa','النساء','Week 4 - Chapter 4',1,23,0,'الأسبوع 4 - الجزء 4')," +
                "('An-Nisaa','النساء','Week 4 - Chapter 4',1,23,0,'الأسبوع 4 - الجزء 4')," +
                "('An-Nisaa','النساء','Week 5 - Chapter 5',24,147,0,'لأسبوع 5 - الجزء 5')," +
                "('An-Nisaa','النساء','Week 6 - Chapter 6',148,176,0,'الأسبوع 6 - الجزء 6')," +
                "('Al-Maedah','المائدة','Week 6 - Chapter 6',1,81,0,'الأسبوع 6 - الجزء 6')," +
                "('Al-Maedah','المائدة','Week 7 - Chapter 7',82,120,0,'الأسبوع 7 - الجزء 7')," +
                "('Al-An\''am','الأنعام','Week 7 - Chapter 7',1,110,0,'الأسبوع 7 - الجزء 7')," +
                "('Al-An\''am','الأنعام','Week 8 - Chapter 8',111,165,0,'الأسبوع 8 - الجزء 8')," +
                "('Al-A\''raf','الأعراف','Week 8 - Chapter 8',1,87,0,'الأسبوع 8 - الجزء 8')," +
                "('Al-A\''raf','الأعراف','Week 9 - Chapter 9',1,40,0,'الأسبوع 9 - الجزء 9')," +
                "('Al-A\''raf','الأعراف','Week 9 - Chapter 9',1,40,0,'الأسبوع 9 - الجزء 9');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE_30);
// Creating tables again
        onCreate(db);
    }

    // Adding new shop
   /* public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName()); // Shop Name
        // Shop Phone Number
        values.put(KEY_TIME,food.getTime());
        values.put(KEY_TEMP, food.getTemp());
// Inserting Row
        db.insert(TABLE_FOODS, null, values);
        db.close(); // Closing database connection
    }*/
    // Getting one shop
   /* public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOODS, new String[]{KEY_ID,
                        KEY_NAME,KEY_TEMP,KEY_TIME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Food contact = new Food(Integer.parseInt(cursor.getString(3)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
// return shop
        return contact;
    }
    // Getting All Shops
    public List<Food> getAllFoods() {
        List<Food> shopList = new ArrayList<Food>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FOODS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setTemp(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setTime(Integer.parseInt(cursor.getString(2)));
// Adding contact to list
                shopList.add(food);
            } while (cursor.moveToNext());
        }

// return contact list
        return shopList;
    }*/
    // Getting shops Count
//    public int getFoodsCount(String table_name) {
//        String countQuery = "SELECT * FROM " + table_name;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//// return count
//        return cursor.getCount();
//    }
    // Updating a shop
  /*  public int updateShop(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_TEMP, food.getTemp());

        values.put(KEY_TIME, food.getTime());
// updating row
        return db.update(TABLE_FOODS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(food.getId())});
    }*/

    // Deleting a shop
    public Cursor getAllRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =db.rawQuery( "select AyatId _id,* from schedule30", null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public void updateStatus(long id,int status){
        String query = "update schedule30 set status="+status+" where AyatId="+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

        db.close();
    }
    public void restStatus(){
        String query = "update schedule30 set status=0";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);

        db.close();
    }
    public int getReadCount(String column) {
        String countQuery = "SELECT * FROM " + TABLE_SCHEDULE_30+" where status=1 group by "+column+"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);


// return count
        return cursor.getCount();
    }
    public float getUnReadCount(String column) {
        String countQuery = "SELECT * FROM " + TABLE_SCHEDULE_30+" where status=0 group by "+column+"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);


// return count
        return cursor.getCount();
    }
}
