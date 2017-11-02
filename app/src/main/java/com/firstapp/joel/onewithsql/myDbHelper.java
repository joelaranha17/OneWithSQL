package com.firstapp.joel.onewithsql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joel on 10/29/2017.
 */

class MyDbHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "mydb";
    public final static String TABLE_NAME = "my_table";
    public final static String USERNAME = "my_username";
    public final static String PASSWORD = "my_password";
    public final static String ID = "my_id";

    public final static int VERSION =1 ;

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String my_query = "create table my_table (my_username VARCHAR, my_password VARCHAR, my_id VARCHAR)";
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + USERNAME + " TEXT,"
                + PASSWORD + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
//------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int myOld, int myNew) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
