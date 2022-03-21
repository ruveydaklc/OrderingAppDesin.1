package com.example.snav1.dbmnager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper (context: Context)
    : SQLiteOpenHelper(context,"ProductDB.sqlite",null,1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS \"Product\" (\n" +
                "\t\"Id\"\tINTEGER,\n" +
                "\t\"Name\"\tTEXT,\n" +
                "\t\"Price\"\tREAL,\n" +
                "\t\"Type\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"Id\" AUTOINCREMENT)\n" +
                ")")

    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Product")
        onCreate(db)
    }
}