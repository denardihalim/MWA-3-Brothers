package com.example.onehelp.myDB

import android.provider.BaseColumns

object userDB {
    class userTable: BaseColumns {
        companion object {
            val TABLE_USER = "user"
            val COLUMN_ID: String = "_id"
            val COLUMN_NAME: String = "nama"
            val COLUMN_EMAIL: String = "email"
            val COLUMN_PHONE: String = "phone"
        }
    }
}