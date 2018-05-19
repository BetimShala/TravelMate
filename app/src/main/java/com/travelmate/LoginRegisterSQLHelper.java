package com.travelmate;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LoginRegisterSQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TravelMateDBContext";
    public static final String TABLE_NAME = "Register_Login";
    public static final String FULLNAME = "fullname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String REGISTER_AS = "registeras";
    public static final String _ID = BaseColumns._ID;
    public static final int DB_VER = 6;

    public LoginRegisterSQLHelper(Context context) {
        //1 is todo list database version
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createRegisterLoginTable = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FULLNAME + " TEXT, "+EMAIL+" TEXT UNIQUE,"+PASSWORD+" TEXT,"+AGE+" INTEGER,"+GENDER+" TEXT,"+REGISTER_AS+" TEXT)";
        sqLiteDatabase.execSQL(createRegisterLoginTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewUser(String fullName,String email,String password,Integer age,String gender,String registeras)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FULLNAME,fullName);
        cv.put(EMAIL,email);
        cv.put(PASSWORD,password);
        cv.put(GENDER,gender);
        cv.put(REGISTER_AS,registeras);
        cv.put(AGE,age);

        db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor userCursor = db.rawQuery("SELECT  * FROM "+TABLE_NAME, null);
        //Cursor cursor = db.query(TABLE_NAME, new String[]{COL1_TASK,COL2_TASK}, null, null, null, null, null);
  /*      while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(COL1_TASK);
            int index2 = cursor.getColumnIndex(COL2_TASK);
            tasks.add(cursor.getString(index1)+"-"+cursor.getString(index2));

        }
        cursor.close();
        db.close();*/
        //cursor.moveToFirst();
        return userCursor;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}