package com.example.onehelp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.onehelp.myDB.userDB

@RequiresApi(Build.VERSION_CODES.P)
class myDBHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USER_TABLE = ("CREATE TABLE " +
                userDB.userTable.TABLE_USER+ "(" +
                userDB.userTable.COLUMN_ID+ " INTEGER PRIMARY KEY," +
                userDB.userTable.COLUMN_NAME + " TEXT," +
                userDB.userTable.COLUMN_EMAIL + " TEXT," +
                userDB.userTable.COLUMN_PHONE+ " TEXT" + ")")
        db?.execSQL(CREATE_USER_TABLE)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " +
                userDB.userTable.TABLE_USER)
        onCreate(db)
    }
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mypreloadcontent.db"
    }
}