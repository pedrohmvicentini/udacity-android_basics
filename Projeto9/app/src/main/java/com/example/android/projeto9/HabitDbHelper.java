package com.example.android.projeto9;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pedro on 29/01/2018.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projetonove.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_HABITS = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + "("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HabitContract.HabitEntry.COLUMN_ORDER + " INTEGER NOT NULL,"
                + HabitContract.HabitEntry.COLUMN_DESCRIPTION + " STRING"
                + ");";

        Log.v("HabitDbHelper", "create table: " + CREATE_TABLE_HABITS);
        db.execSQL(CREATE_TABLE_HABITS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(int order, String description) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_ORDER, order);
        values.put(HabitContract.HabitEntry.COLUMN_DESCRIPTION, description);
        db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor read() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_ORDER,
                HabitContract.HabitEntry.COLUMN_DESCRIPTION
        };

        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }
}