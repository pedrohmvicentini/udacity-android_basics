package com.example.android.projeto9;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper habitDbHelper = new HabitDbHelper(this);

        habitDbHelper.insert(1, "Habit");

        Cursor cursor = habitDbHelper.read();
        while (cursor.moveToNext()) {
            Log.v(TAG, "habit: "
                    + cursor.getInt(0) + "|"
                    + cursor.getInt(1) + "|"
                    + cursor.getInt(2));
        }
    }
}
