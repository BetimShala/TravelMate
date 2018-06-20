package com.travelmate.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LoginRegisterRepository extends SQLiteOpenHelper {

    public static final String DB_NAME = "TravelMateDBContext";
    public static final String TABLE_NAME = "Register_Login";
    public static final String FULLNAME = "fullname";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String REGISTER_AS = "registerAs";
    public static final String _ID = BaseColumns._ID;
    public static final int DB_VER = 10;
    public  String fullname=null,email=null;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginRegisterRepository(Context context) {
        //1 is todo list database version
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createRegisterLoginTable = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FULLNAME + " TEXT, "+USERNAME+" TEXT, "+EMAIL+" TEXT UNIQUE,"+PASSWORD+" TEXT, "+AGE+" INTEGER, "+GENDER+" TEXT,"+REGISTER_AS+" TEXT)";
        sqLiteDatabase.execSQL(createRegisterLoginTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addNewUser(String fullName,String userName,String email,String password,String gender,String registerAs,Integer age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FULLNAME,fullName);
        cv.put(USERNAME,userName);
        cv.put(EMAIL,email);
        cv.put(PASSWORD,password);
        cv.put(GENDER,gender);
        cv.put(REGISTER_AS,registerAs);
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

    public boolean isUser(String username,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor exist = db.rawQuery("SELECT  * FROM "+TABLE_NAME+" WHERE "+USERNAME+"='"+username+"' and "+PASSWORD+"='"+password+"';", null);

        if (exist.getCount()>=1){

            return true;
        }
        else
            return false;
    }

    public Cursor getInfo(LoginRegisterRepository dop)
    {
    SQLiteDatabase SQ = dop.getReadableDatabase();
    String columns[] = {FULLNAME, USERNAME, EMAIL, PASSWORD, AGE, GENDER, REGISTER_AS};
    Cursor CR = SQ.query(TABLE_NAME, columns, null, null, null, null, null);
    return CR;
    }


}