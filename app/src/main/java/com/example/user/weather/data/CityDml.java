package com.example.user.weather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 21/9/2017.
 */

public class CityDml {

    public   Ddl ddl ;
    ArrayList<Weather> weathers;
    ArrayList<String> weatherst;

    public Context context;
    public CityDml(Context context) {
        this.context=context;
    }

    public void insertData(String city,String area,double latitude,double longitude){

        ContentValues values=new ContentValues();
        values.put(DbContract.CityEntry.COLUMN_CITY_LATITUTE,latitude);
        values.put(DbContract.CityEntry.COLUMN_CITY_LONGITUTE,longitude);
        values.put(DbContract.CityEntry.COLUMN_CITY_AREA,area);
        values.put(DbContract.CityEntry.COLUMN_CITY_NAME,city);






        ddl=new Ddl(context);
        SQLiteDatabase db = ddl.getWritableDatabase();
        long x= db.insert(DbContract.CityEntry.TABLE_NAME, null, values);
        Log.e("row count","="+x);
    }

    public void updateData(){}
    public void deleteData(){}

    public ArrayList<String> selectData(){
        ddl=new Ddl(context);
        SQLiteDatabase db = ddl.getReadableDatabase();

        String[] projection = {
                DbContract.CityEntry.COLUMN_CITY_NAME,
                DbContract.CityEntry.COLUMN_CITY_LATITUTE,
                DbContract.CityEntry.COLUMN_CITY_LONGITUTE};

        Cursor cursor = db.query(
                DbContract.CityEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {


            int nameColumnIndex = cursor.getColumnIndex(DbContract.CityEntry.COLUMN_CITY_NAME);
            int latColumnIndex = cursor.getColumnIndex(DbContract.CityEntry.COLUMN_CITY_LONGITUTE);
            int lonColumnIndex = cursor.getColumnIndex(DbContract.CityEntry.COLUMN_CITY_LONGITUTE);



            weatherst=new ArrayList<>();
            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                double currentLat = cursor.getDouble(latColumnIndex);
                double currentLog = cursor.getDouble(lonColumnIndex);


                weatherst.add(currentName);
                //weathers.add(new Weather(currentName,"demo",1,1));


            }
        } finally {
            cursor.close();
        }


        return weatherst;
    }

    public ArrayList<String> selectLat(){
        ddl=new Ddl(context);

        SQLiteDatabase db = ddl.getReadableDatabase();

        String[] projection = {
                DbContract.CityEntry.COLUMN_CITY_NAME,
                DbContract.CityEntry.COLUMN_CITY_LATITUTE,
                DbContract.CityEntry.COLUMN_CITY_LONGITUTE};

        Cursor cursor = db.query(
                DbContract.CityEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {


            int nameColumnIndex = cursor.getColumnIndex(DbContract.CityEntry.COLUMN_CITY_NAME);
            int latColumnIndex = cursor.getColumnIndex(DbContract.CityEntry.COLUMN_CITY_LONGITUTE);
            int lonColumnIndex = cursor.getColumnIndex(DbContract.CityEntry.COLUMN_CITY_LONGITUTE);


            weatherst=new ArrayList<>();

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColumnIndex);
                double currentLat = cursor.getDouble(latColumnIndex);
                double currentLog = cursor.getDouble(lonColumnIndex);


                weatherst.add(""+currentLat);
                //weathers.add(new Weather(currentName,"demo",1,1));


            }
        } finally {
            cursor.close();
        }


        return weatherst;
    }

}
