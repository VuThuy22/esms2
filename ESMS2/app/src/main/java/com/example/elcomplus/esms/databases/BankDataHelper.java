package com.example.elcomplus.esms.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.elcomplus.esms.models.Bank;

import java.util.ArrayList;

public class BankDataHelper extends SQLiteOpenHelper {
    private static final String BANK_ID="ID";
    private static final String BANK_PHONE="BANK_PHONE";
    private static final String BANK_NAME="BANK_NAME";
    private static final String SMS_BANK_DATA="SMS_BANK_DATA";
    private static final String BANK_TABLE_NAME="BANK_TABLE_NAME";
    public BankDataHelper(Context context) {
        super(context, SMS_BANK_DATA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE " + BANK_TABLE_NAME+ " ( " +BANK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "
                + BANK_NAME +" TEXT , "
                + BANK_PHONE +" TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IF EXISTS "+BANK_TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public long addBank(Bank bank){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(BANK_NAME,bank.getBankName());
        values.put(BANK_PHONE,bank.getBankPhone());
        long id= db.insert(BANK_TABLE_NAME, null, values);
        db.close();
        return id;
    }
    public ArrayList<Bank> getAll() {
        ArrayList<Bank> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + BANK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getAll: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                Bank bank = new Bank();
                bank.setId(cursor.getInt(cursor.getColumnIndex(BANK_ID)));
                bank.setBankPhone(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));
                bank.setBankName(cursor.getString(cursor.getColumnIndex(BANK_NAME)));
                list.add(bank);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public ArrayList<String> getBankPhone() {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + BANK_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Log.d("ttt", "getcolum: " + cursor);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex(BANK_PHONE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void deletebank(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BANK_TABLE_NAME, BANK_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
