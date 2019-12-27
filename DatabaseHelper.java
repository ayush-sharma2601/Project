package com.example.mydata;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Defining Fields
    public static final String database_name ="cte.db";
    public static final String table1 ="MONDAY";
    public static final String table2 ="TUESDAY";
    public static final String table3 ="WEDNESDAY";
    public static final String table4 ="THURSDAY";
    public static final String table5 ="FRIDAY";
    public static final String table6 ="SATURDAY";
    public static final String col1 = "TIME";
    public static final String col2 = "PERIOD";
    public static final String col3 = "PLACE";
    public static final String col4 = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table1+ " (TIME TEXT, PERIOD TEXT, PLACE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");
        db.execSQL("create table "+table2+ " (TIME TEXT, PERIOD TEXT, PLACE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");
        db.execSQL("create table "+table3+ " (TIME TEXT, PERIOD TEXT, PLACE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");
        db.execSQL("create table "+table4+ " (TIME TEXT, PERIOD TEXT, PLACE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");
        db.execSQL("create table "+table5+ " (TIME TEXT, PERIOD TEXT, PLACE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");
        db.execSQL("create table "+table6+ " (TIME TEXT, PERIOD TEXT, PLACE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ table1);
        db.execSQL("drop table if exists "+ table2);
        db.execSQL("drop table if exists "+ table3);
        db.execSQL("drop table if exists "+ table4);
        db.execSQL("drop table if exists "+ table5);
        db.execSQL("drop table if exists "+ table6);
        onCreate(db);

    }

    public boolean insertData(String time, String period, String place,String table_name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,time);
        contentValues.put(col2,period);
        contentValues.put(col3,place);

        long is = db.insert(table_name,null,contentValues);
        if ( is==-1)
            return false;

        else
            return true;


    }

    public Cursor getdata(String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name,null);
        return res;
    }

    public void delete(String ID,String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,"Time = ?", new String[] {ID});
    }
}
