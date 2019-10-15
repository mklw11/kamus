package com.example.kamus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kamus";
    public static final String POLANDIA = "polandia";
    public static final String INDONESIA = "indonesia";
    public static final String KUTAI = "kutai";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        db.execSQL("CREATE TABLE if not exists " + DATABASE_NAME + " (_id INTEGER PRIMARY KEY" +
                " AUTOINCREMENT, indonesia TEXT, polandia TEXT, kutai TEXT);");
    }

    public void generateData(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(INDONESIA,"Saya");
        cv.put(POLANDIA,"Ja");
        cv.put(KUTAI,"Nyawa");
        db.insert("kamus", "", cv);
        cv.put(INDONESIA,"Beli");
        cv.put(POLANDIA,"Kup to");
        cv.put(KUTAI,"Beli");
        db.insert("kamus", POLANDIA, cv);
        cv.put(INDONESIA,"Ingin");
        cv.put(POLANDIA,"Want");
        cv.put(KUTAI,"Handak");
        db.insert("kamus", KUTAI, cv);
    }
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //TODO Auto-generated method stub
        createTable(db);
        generateData(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //TODO Auto-generated method stub
        createTable(db);
        generateData(db);
    }
}

