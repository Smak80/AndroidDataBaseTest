package ru.smak.databasetest.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import ru.smak.databasetest.ui.main.Person

class DataBaseHelper private constructor(
    context: Context
) : SQLiteOpenHelper(context, "database", null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        // INTEGER, REAL, TEXT, BLOB
        p0?.execSQL("CREATE TABLE $TABLE_NAME (" +
                "id integer not null primary key autoincrement," +
                "$F_NAME text not null," +
                "$F_AGE integer not null," +
                "$F_SALARY real not null)"
        )

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun save(person: Person) {
        val cv = ContentValues()
        cv.put(F_NAME, person.name)
        cv.put(F_AGE, person.age)
        cv.put(F_SALARY, person.salary)
        with (writableDatabase){
            beginTransaction()
            val result = insert(TABLE_NAME, "", cv)
            if (result > 0){
                setTransactionSuccessful()
            }
            Log.d("RECORDS INSERTED", result.toString())
            endTransaction()
        }
    }

    @SuppressLint("Range")
    fun load(): List<Person> {
        val lst = mutableListOf<Person>()
        with(readableDatabase){
            beginTransaction()
            val cursor = query(
                TABLE_NAME,
                arrayOf(F_NAME, F_AGE, F_SALARY),
                null,
                null,
                null,
                null,
                F_NAME
            )
            while (cursor.moveToNext()){
                lst.add(
                    Person(
                        cursor.getString(cursor.getColumnIndex(F_NAME)),
                        cursor.getInt(cursor.getColumnIndex(F_AGE)),
                        cursor.getFloat(cursor.getColumnIndex(F_SALARY))
                    )
                )
            }
            cursor.close()
            if (lst.count()>0) setTransactionSuccessful()
            endTransaction()
        }
        return lst
    }

    companion object{
        private var instance: DataBaseHelper? = null

        fun getInstance(context: Context) =
            instance ?: DataBaseHelper(context).also{ instance = it }

        const val TABLE_NAME = "main"
        const val F_NAME = "name"
        const val F_AGE = "age"
        const val F_SALARY = "salary"
    }
}