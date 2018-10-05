package com.example.android.projeto10.data;

import android.provider.BaseColumns;

/**
 * Created by Pedro on 09/02/2018.
 */

public class InventoryContract {
    private InventoryContract() {
    }

    public static final class InventoryEntry implements BaseColumns {

        public static final String TABLE_NAME = "Inventory";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TITLE = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "supplier_email";
        public static final String COLUMN_IMAGE = "image";

        public static final String CREATE_TABLE_Inventory = "CREATE TABLE " +
                InventoryContract.InventoryEntry.TABLE_NAME + "(" +
                InventoryContract.InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                InventoryContract.InventoryEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                InventoryContract.InventoryEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                InventoryContract.InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                InventoryContract.InventoryEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                InventoryEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
