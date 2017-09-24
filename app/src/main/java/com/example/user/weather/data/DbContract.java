package com.example.user.weather.data;

import android.provider.BaseColumns;

/**
 * Created by user on 11/9/2017.
 */

public class DbContract {

    //TODO-1: Constructor
    private DbContract() {}



    public static final class CityEntry implements BaseColumns {


        public final static String TABLE_NAME = "city";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_CITY_NAME ="city";
        public final static String COLUMN_CITY_AREA = "area";
        public final static String COLUMN_CITY_LATITUTE ="latitude";
        public final static String COLUMN_CITY_LONGITUTE = "longitude";




    }


}
