package com.example.DataPresistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapter {
    private Context context;
    static DataBaseHelper dbHelper;
    private boolean englishFlag = true;

    public DataBaseAdapter(Context _context) {
        dbHelper = new DataBaseHelper(_context);
        context = _context;
    }

    static class DataBaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "atm_guide_db.db";
        private static final String BANK_TABLE_NAME = "BANK";
        private static final String UID = "_id";
        private static final String BANK_ID = "bnk_id";
        private static final String BANK_NAME = "bnk_name";
        private static final String BANK_NAME_AR = "bnk_name_ar";
        private static final String BANK_HOT_NUM = "bnk_hot_num";
        private static final String LOGO_PATH = "logo_path";
        private static final String BANK_URL = "bnk_url";
        private static final String BANK_MODIFIED_DATE = "bnk_modified_date";
        private static final String LOGO_MODIFIED_DATE = "logo_modified_date";

        private static final String CREATE_BANK_TABLE = "CREATE TABLE " + BANK_TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BANK_ID + " INTEGER," + BANK_NAME + " TEXT, " + BANK_NAME_AR + " TEXT, " + BANK_HOT_NUM + " TEXT, " +
                LOGO_PATH + " TEXT, " + BANK_URL + " TEXT, " + BANK_MODIFIED_DATE + " TEXT, " +
                LOGO_MODIFIED_DATE + " TEXT);";

        private static final String SPONSER_TABLE_NAME = "SPONSER";
        private static final String SPONSER_UID = "_id";
        private static final String SPONSER_ID = "bnk_id";
        private static final String SPONSER_URL = "bnk_url";
        private static final String SPONSER_LOGO_PATH = "logo_path";
        private static final String SPONSER_LOGO_MODIFIED_DATE = "logo_modified_date";

        private static final String CREATE_SPONSER_TABLE = "CREATE TABLE " + SPONSER_TABLE_NAME + " (" + SPONSER_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SPONSER_ID + " INTEGER," + SPONSER_LOGO_PATH + " TEXT, " + SPONSER_URL + " TEXT, " +
                SPONSER_LOGO_MODIFIED_DATE + " TEXT);";

        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_BANK_TABLE);
            db.execSQL(CREATE_SPONSER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }
    }
}