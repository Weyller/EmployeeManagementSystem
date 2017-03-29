package com.pranaykumar.dbmsproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "MyDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Employee";

    private static final String COLUMN_EID="eid";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_PHONE="phone";
    private static final String COLUMN_GENDER="gender";
    private static final String COLUMN_EMAIL="email";

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+"("+
                COLUMN_EID + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_EMAIL + " TEXT " +
                ");";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public boolean userExists(String usereid) {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE "+ COLUMN_EID +"=\"" + usereid + "\";";
        Cursor c=db.rawQuery(query, null);
        boolean b = c.moveToFirst();
        c.close();
        return b;
    }

    public void addRow(EmployeeDetails item) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_EID, item.getEid());
            values.put(COLUMN_NAME, item.getName());
            values.put(COLUMN_PHONE, item.getPhone());
            values.put(COLUMN_GENDER, item.getGender());
            values.put(COLUMN_EMAIL, item.getEmail());

            db.insert(TABLE_NAME, null, values);
            db.close();
        }
        catch (Exception e){
            Log.w("TAG", "Error while adding user to database");
        }
    }

    public void deleteRow(String eid) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_EID + "=\"" + eid + "\";");
    }

    public String getName(String usereid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EID + "=\"" + usereid +"\";";
        Cursor c = db.rawQuery(query, null);
        try {
            c.moveToFirst();
            String s= c.getString(c.getColumnIndex(COLUMN_NAME));
            c.close();
            return s;
        } catch (Exception e) {
            Log.w("TAG", "Error while getting Name from database");
            return null;
        }
    }

    public String getPhone(String usereid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EID + "=\"" + usereid +"\";";
        Cursor c = db.rawQuery(query, null);
        try {
            c.moveToFirst();
            String s= c.getString(c.getColumnIndex(COLUMN_PHONE));
            c.close();
            return s;
        } catch (Exception e) {
            Log.w("TAG", "Error while getting Phone from database");
            return null;
        }
    }

    public String getGender(String usereid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EID + "=\"" + usereid +"\";";
        Cursor c = db.rawQuery(query, null);
        try {
            c.moveToFirst();
            String s= c.getString(c.getColumnIndex(COLUMN_GENDER));
            c.close();
            return s;
        } catch (Exception e) {
            Log.w("TAG", "Error while getting Gender from database");
            return null;
        }
    }

    public String getEmail(String usereid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EID + "=\"" + usereid +"\";";
        Cursor c = db.rawQuery(query, null);
        try {
            c.moveToFirst();
            String s= c.getString(c.getColumnIndex(COLUMN_EMAIL));
            c.close();
            return s;
        } catch (Exception e) {
            Log.w("TAG", "Error while getting Email from database");
            return null;
        }
    }

    public ArrayList<String> getAllUsers()
    {
        ArrayList<String> mlist=new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        try {
            if(c.moveToFirst())
                do {
                    String s=c.getString(c.getColumnIndex(COLUMN_EID));
                    mlist.add(s);
                }while (c.moveToNext());
        }catch (Exception e)
        {
            Log.w("TAG", "getAllUsers: "+ e.getMessage());
        }
        c.close();
        return mlist;
    }

}
