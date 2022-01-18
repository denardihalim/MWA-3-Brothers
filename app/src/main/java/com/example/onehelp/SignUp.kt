package com.example.onehelp

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Telephony
import android.view.View
import android.widget.*
import androidx.loader.content.AsyncTaskLoader

private val PrefFileName = "MYFILEPREF01"
class SignUp : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        /*var su_imageButton1_toFirstPage = findViewById<ImageButton>(R.id.su_imageButton1)
        su_imageButton1_toFirstPage.setOnClickListener{
            var intentHome= Intent(this,FirstPage::class.java)
            startActivity(intentHome)
        }

        var su_button1_toHomePage = findViewById<Button>(R.id.su_button1)
        su_button1_toHomePage.setOnClickListener{
            var intentHome= Intent(this,HomePage::class.java)
            startActivity(intentHome)
        }

        var su_textView6_toLogin = findViewById<TextView>(R.id.su_textView6)
        su_textView6_toLogin.setOnClickListener{
            var intentHome= Intent(this,Login::class.java)
            startActivity(intentHome)
        }*/

        var btnSubmit = findViewById<Button>(R.id.btn_submit)
        btnSubmit.setOnClickListener(this)

        var btnFind = findViewById<Button>(R.id.su_button1)
        btnFind.setOnClickListener(this)

        var btnReset = findViewById<Button>(R.id.btn_reset)
        btnReset.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var kolomNama = findViewById<EditText>(R.id.su_editTextTextPersonName1)
        var kolomEmail = findViewById<EditText>(R.id.su_editTextTextEmailAddress1)
        var kolomTelepon = findViewById<EditText>(R.id.su_editTextPhone1)
        var sharePrefHelper = SharePrefHelper(this, PrefFileName)
        when(p0?.id){
            R.id.btn_submit -> {
                sharePrefHelper.nama = kolomNama.text.toString()
                sharePrefHelper.email = kolomEmail.text.toString()
                sharePrefHelper.no_hp= kolomTelepon.text.toString()
                Toast.makeText(this,"Tersimpan",Toast.LENGTH_SHORT).show()
                kolomNama.text.clear()
                kolomEmail.text.clear()
                kolomTelepon.text.clear()
            }

            R.id.btn_reset -> {
                sharePrefHelper.clearValues()
                kolomNama.text.clear()
                kolomEmail.text.clear()
                kolomTelepon.text.clear()
            }

            R.id.su_button1 ->{
                kolomNama.setText(sharePrefHelper.nama)
                kolomEmail.setText(sharePrefHelper.email)
                kolomTelepon.setText(sharePrefHelper.no_hp)
            }
        }
    }
}