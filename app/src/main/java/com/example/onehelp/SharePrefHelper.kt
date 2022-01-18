package com.example.onehelp

import android.content.Context
import android.content.SharedPreferences

class SharePrefHelper(context: Context, name: String) {
    val USER_NAME = "NAMA"
    val USER_EMAIL = "EMAIL"
    val USER_PHONE = "PHONE"

    private var myPreferences : SharedPreferences

    init{
        myPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.commit()
    }

    var nama : String?
        get() = myPreferences.getString(USER_NAME, "Kosong")
        set(value) {
            myPreferences.editMe {
                it.putString(USER_NAME, value)
            }
        }

    var email : String?
        get() = myPreferences.getString(USER_EMAIL, "Kosong")
        set(value) {
            myPreferences.editMe {
                it.putString(USER_EMAIL, value)
            }
        }

    var no_hp: String?
        get() = myPreferences.getString(USER_PHONE, "Kosong")
        set(value) {
            myPreferences.editMe {
                it.putString(USER_PHONE, value)
            }
        }

    fun clearValues() {
        myPreferences.edit().clear().commit()
    }
}