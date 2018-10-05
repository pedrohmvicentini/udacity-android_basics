package com.example.android.projeto9;

import android.provider.BaseColumns;

/**
 * Created by Pedro on 29/01/2018.
 */

public class HabitContract {

    public HabitContract() {
    }

    public class HabitEntry implements BaseColumns {
        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ORDER = "number";
        public final static String COLUMN_DESCRIPTION = "description";
    }
}
