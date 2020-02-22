package com.example.DataPresistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseAdapter {
    private Context context;
    static DataBaseHelper dbHelper;

    public DataBaseAdapter(Context _context) {
        dbHelper = new DataBaseHelper(_context);
        context = _context;
    }

    public long insertContact(ContactDTO contactDTO) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NAME, contactDTO.getName());
        contentValues.put(DataBaseHelper.PHONE_NUMBER, contactDTO.getPhone());
        long id = db.insert(DataBaseHelper.CONTACT_TABLE_NAME, null, contentValues);

        return id;
    }

    public ContactDTO getLastContact() {
        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DataBaseHelper.NAME,
                DataBaseHelper.PHONE_NUMBER};
        c = db.query(DataBaseHelper.CONTACT_TABLE_NAME, columns, null, null, null, null, null);

        c.moveToLast();
        ContactDTO contact = new ContactDTO();
        contact.setName("" + c.getString(0));
        contact.setPhone(c.getString(1));

        return contact;
    }

    public ContactDTO[] getAllContacts() {
        ContactDTO[] contactDTOS = null;
        int i = 0;
        Cursor c;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DataBaseHelper.NAME,
                DataBaseHelper.PHONE_NUMBER};
        //query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        c = db.query(DataBaseHelper.CONTACT_TABLE_NAME, columns, null, null, null, null, null);
        contactDTOS = new ContactDTO[c.getCount()];
        while (c.moveToNext()) {
            ContactDTO sponser = new ContactDTO();
            sponser.setName("" + c.getString(0));
            sponser.setPhone(c.getString(1));
            contactDTOS[i++] = sponser;
        }
        return contactDTOS;
    }

    static class DataBaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "contacts_db.db";
        private static final String CONTACT_TABLE_NAME = "CONTACT";
        private static final String UID = "_id";
        private static final String NAME = "name";
        private static final String PHONE_NUMBER = "phone_number";

        private static final String CREATE_BANK_TABLE = "CREATE TABLE " + CONTACT_TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " +
                PHONE_NUMBER + " TEXT, " + " TEXT);";


        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_BANK_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }
    }
}