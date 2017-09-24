package com.example.user.weather.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.weather.data.DbContract.CityEntry;


/**
 * Created by user on 11/9/2017.
 */

public class Ddl extends SQLiteOpenHelper {

    // TODO-1: database name and version//
    public static final String LOG_TAG = Ddl.class.getSimpleName();
    private static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    // TODO-2: constructor //
    public Ddl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO-3: @Override two mathod of SQLiteOpenHelper.class//
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_CITY_TABLE =  "CREATE TABLE " + CityEntry.TABLE_NAME + " ("
                + CityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CityEntry.COLUMN_CITY_NAME + " TEXT NOT NULL, "
                + CityEntry.COLUMN_CITY_AREA + " TEXT NOT NULL, "
                + CityEntry.COLUMN_CITY_LATITUTE + " NUMERIC , "
                + CityEntry.COLUMN_CITY_LONGITUTE + " NUMERIC );";

        db.execSQL(SQL_CREATE_CITY_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " +  CityEntry.TABLE_NAME);
        onCreate(db);

    }


}
