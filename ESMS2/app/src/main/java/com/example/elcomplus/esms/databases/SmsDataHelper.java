package com.example.elcomplus.esms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elcomplus.esms.models.Bank;
import com.example.elcomplus.esms.models.Sms;

import java.util.ArrayList;

public class SmsDataHelper extends SQLiteOpenHelper {
    private final static String SMS_TABLE_NAME="SMS_TABLE_NAME";
    private static final String SMS_BANK_DATA="SMS_BANK_DATA";
    private static final String SMS_ID="SMS_ID";
    private static final String SMS_BANK="SMS_BANK";
    private static final String SMS_CONTENT="SMS_CONTENT";
    private static final String SMS_STATUS="SMS_STATUS";
    private static final String SMS_TIME="SMS_TIME";
    private static final String SMS_PHONE="SMS_PHONE";
    public SmsDataHelper(Context context) {
        super(context, SMS_BANK_DATA, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=" CREATE TABLE " + SMS_TABLE_NAME+ " ( " +SMS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "
                + SMS_BANK +" TEXT, "
                + SMS_CONTENT +" TEXT, "
                + SMS_STATUS +" TEXT, "
                + SMS_PHONE +" TEXT, "
                + SMS_TIME +" TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS "+SMS_TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public void editBank(Sms sms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SMS_STATUS, sms.getStatus());
        db.update(SMS_TABLE_NAME, values, SMS_ID + "=?", new String[]{String.valueOf(sms.getSmsId())});
        db.close();
    }

    public void addSms(Sms sms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SMS_BANK, sms.getBankName());
        values.put(SMS_CONTENT, sms.getContent());
        values.put(SMS_STATUS, sms.getStatus());
        values.put(SMS_TIME, sms.getTime());
        values.put(SMS_PHONE, sms.getPhone());
        db.insert(SMS_TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<Sms> getAll() {
        ArrayList<Sms> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SMS_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getAll: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                Sms sms = new Sms();
                sms.setStatus(cursor.getInt(cursor.getColumnIndex(SMS_STATUS)));
                sms.setBankName(cursor.getString(cursor.getColumnIndex(SMS_BANK)));
                sms.setContent(cursor.getString(cursor.getColumnIndex(SMS_CONTENT)));
                list.add(sms);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

}
