package com.afrometal.radoslaw.doitlater;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by radoslaw on 26.04.17.
 */

public class ToDoDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDo.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoContract.ToDoEntry.TABLE_NAME + " (" +
                    ToDoContract.ToDoEntry._ID + " INTEGER PRIMARY KEY," +
                    ToDoContract.ToDoEntry.COLUMN_NAME_TITLE + " TEXT," +
                    ToDoContract.ToDoEntry.COLUMN_NAME_DETAILS + " TEXT," +
                    ToDoContract.ToDoEntry.COLUMN_NAME_DATE + " TIMESTAMP)";

    public ToDoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insert(String title, String details) {
        long time = System.currentTimeMillis();

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues(3);
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_DATE, time);
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE, title);
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_DETAILS, details);

        return db.insert(ToDoContract.ToDoEntry.TABLE_NAME, null, values);
    }

    public int update(Long id, String title, String details) {
        long time = System.currentTimeMillis();

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues(3);
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_DATE, time);
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_TITLE, title);
        values.put(ToDoContract.ToDoEntry.COLUMN_NAME_DETAILS, details);

        String selection = ToDoContract.ToDoEntry._ID + " = ?";
        String[] selectionArgs = { id.toString() };

        return db.update(
                ToDoContract.ToDoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
