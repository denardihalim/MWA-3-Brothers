package com.example.onehelp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class Login : AppCompatActivity(), FirstView {

    lateinit var callbackManager: CallbackManager
    //Deklarasi presenter
    lateinit var presenterImp: FirstPresenterImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        var login_imageButton1_toFirstPage = findViewById<ImageButton>(R.id.login_imageButton1)
        login_imageButton1_toFirstPage.setOnClickListener{
            var intentHome= Intent(this,FirstPage::class.java)
            startActivity(intentHome)
        }

        var login_button1_toHomePage = findViewById<Button>(R.id.login_button1)
        login_button1_toHomePage.setOnClickListener{
            var intentHome= Intent(this,HomePage::class.java)
            startActivity(intentHome)
        }

        var login_button2_toCWF  = findViewById<Button>(R.id.login_button2)
        login_button2_toCWF.setOnClickListener{
            //var intentHome= Intent(this,ContinueWithFacebook::class.java)
            //startActivity(intentHome)
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.facebook.com/"))
            startActivity(intent)
        }

        var login_button3_toCWG  = findViewById<Button>(R.id.login_button3)
        login_button3_toCWG.setOnClickListener{
            var intentHome= Intent(this,ContinueWithGoogle::class.java)
            startActivity(intentHome)
        }

        callbackManager = CallbackManager.Factory.create()

        val loginButton = findViewById<LoginButton>(R.id.login_button2)
        loginButton.setReadPermissions(listOf("public_profile", "email"))

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                Log.d("TAG", "Success Login")
                // Get User's Info
            }

            override fun onCancel() {
                Toast.makeText(this@Login, "Login Cancelled", Toast.LENGTH_LONG).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(this@Login, exception.message, Toast.LENGTH_LONG).show()
            }
        })

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            callbackManager.onActivityResult(requestCode, resultCode, data)
            super.onActivityResult(requestCode, resultCode, data)
        }

        @SuppressLint("LongLogTag")
        fun getUserProfile(token: AccessToken?, userId: String?) {

            val parameters = Bundle()
            parameters.putString(
                    "fields",
                    "id, first_name, middle_name, last_name, name, picture, email"
            )
            GraphRequest(token,
                    "/$userId/",
                    parameters,
                    HttpMethod.GET,
                    GraphRequest.Callback { response ->
                        val jsonObject = response.jsonObject

                        // Facebook Access Token
                        // You can see Access Token only in Debug mode.
                        // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.
                        if (BuildConfig.DEBUG) {
                            FacebookSdk.setIsDebugEnabled(true)
                            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
                        }

                        // Facebook Id
                        if (jsonObject.has("id")) {
                            val facebookId = jsonObject.getString("id")
                            Log.i("Facebook Id: ", facebookId.toString())
                        } else {
                            Log.i("Facebook Id: ", "Not exists")
                        }


                        // Facebook First Name
                        if (jsonObject.has("first_name")) {
                            val facebookFirstName = jsonObject.getString("first_name")
                            Log.i("Facebook First Name: ", facebookFirstName)
                        } else {
                            Log.i("Facebook First Name: ", "Not exists")
                        }


                        // Facebook Middle Name
                        if (jsonObject.has("middle_name")) {
                            val facebookMiddleName = jsonObject.getString("middle_name")
                            Log.i("Facebook Middle Name: ", facebookMiddleName)
                        } else {
                            Log.i("Facebook Middle Name: ", "Not exists")
                        }


                        // Facebook Last Name
                        if (jsonObject.has("last_name")) {
                            val facebookLastName = jsonObject.getString("last_name")
                            Log.i("Facebook Last Name: ", facebookLastName)
                        } else {
                            Log.i("Facebook Last Name: ", "Not exists")
                        }


                        // Facebook Name
                        if (jsonObject.has("name")) {
                            val facebookName = jsonObject.getString("name")
                            Log.i("Facebook Name: ", facebookName)
                        } else {
                            Log.i("Facebook Name: ", "Not exists")
                        }


                        // Facebook Profile Pic URL
                        if (jsonObject.has("picture")) {
                            val facebookPictureObject = jsonObject.getJSONObject("picture")
                            if (facebookPictureObject.has("data")) {
                                val facebookDataObject = facebookPictureObject.getJSONObject("data")
                                if (facebookDataObject.has("url")) {
                                    val facebookProfilePicURL = facebookDataObject.getString("url")
                                    Log.i("Facebook Profile Pic: ", facebookProfilePicURL)
                                }
                            }
                        } else {
                            Log.i("Facebook Profile Pic: ", "Not exists")
                        }

                        // Facebook Email
                        if (jsonObject.has("email")) {
                            val facebookEmail = jsonObject.getString("email")
                            Log.i("Facebook Email: ", facebookEmail)
                        } else {
                            Log.i("Facebook Email: ", "Not exists")
                        }
                    }).executeAsync()
        }
        //Inisialisasi presenter
        presenterImp = FirstPresenterImp(this)

        //Deklarasi variabel tombol dan editText
        var tombolLogin = findViewById<Button>(R.id.login_button1)
        var simpanText = findViewById<EditText>(R.id.login_editTextTextEmailAddress1)
        tombolLogin.setOnClickListener {
            presenterImp.tambahData(simpanText.text.toString())
        }
    }

    override fun berhasil(hasil: ModelMVP) {
        alert {
            title = hasil.textmsgg
            noButton {
            }
            yesButton {d ->
                d.dismiss()
            }
        }
            .show()
    }

    override fun error() {
        toast("Tidak boleh kosong")
    }
}