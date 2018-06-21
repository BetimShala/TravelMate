package com.travelmate.data;

/**
 * Created by Betim on 5/10/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.SQLData;
import java.sql.SQLInput;
import java.util.ArrayList;

public class ToDoListRepository extends SQLiteOpenHelper {

    public static final String DB_NAME = "TravelMateDBContext";
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String COL1_TASK = "todo";
    public static final String COL2_TASK = "time";
    public static final String _ID = BaseColumns._ID;
    public static final int DB_VER = 10;

    public ToDoListRepository(Context context) {
        //1 is todo list database version
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String createTodoListTable = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1_TASK + " TEXT, "+COL2_TASK+" TEXT)";
        sqlDB.execSQL(createTodoListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqlDB);
    }

    public void addNewTask(String task,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_TASK, task);
        cv.put(COL2_TASK,time);
        db.insertWithOnConflict(TABLE_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1_TASK + "= ?", new String[]{task});
        db.close();
    }

    public Cursor getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor todoCursor = db.rawQuery("SELECT  * FROM TODO_LIST ", null);
        //Cursor cursor = db.query(TABLE_NAME, new String[]{COL1_TASK,COL2_TASK}, null, null, null, null, null);
  /*      while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(COL1_TASK);
            int index2 = cursor.getColumnIndex(COL2_TASK);
            tasks.add(cursor.getString(index1)+"-"+cursor.getString(index2));

        }
        cursor.close();
        db.close();*/
        //cursor.moveToFirst();
        return todoCursor;
    }

}