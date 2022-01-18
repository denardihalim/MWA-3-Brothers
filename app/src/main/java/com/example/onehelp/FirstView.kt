package com.example.onehelp

//Membawa data dari presenter ke activity
interface FirstView {

    fun berhasil(hasil : ModelMVP)
    fun error()
}