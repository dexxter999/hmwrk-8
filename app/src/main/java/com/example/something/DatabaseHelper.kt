package com.example.something

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "object_database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "objects"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT NOT NULL)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Implement if needed when upgrading database version
    }

    fun addObject(name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
        Log.d("DatabaseHelper", "Object added: $name")
    }

    @SuppressLint("Range")
    fun getAllObjects(): List<Object> {
        val objectsList = mutableListOf<Object>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        while (cursor.moveToNext()) {
            val objectId = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val objectName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            val obj = Object(id = objectId, name = objectName)
            objectsList.add(obj)
        }

        cursor.close()
        db.close()
        Log.d("DatabaseHelper", "Objects retrieved: $objectsList")

        return objectsList
    }

}
