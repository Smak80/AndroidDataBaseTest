package ru.smak.databasetest.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(
    context: Context
) : SQLiteOpenHelper(context, "database", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        // INTEGER, REAL, TEXT, BLOB
        p0?.execSQL("CREATE TABLE main (" +
                "id integer not null primary key autoincrement," +
                "name text not null," +
                "age integer not null," +
                "salary real not null)"
        )

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}