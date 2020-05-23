package com.abhishek.digital_library

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*


class Dashboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var i: Int = 0

        try {
            val username = user!!.displayName
            if (username != null) {
                username_text.setText(username)
                i = 1
            }
        }
        catch (e:Exception){
        }

        try {
            val user_email = user!!.email
            if (user_email != null) {
                if (i == 0) {
                    username_text.setText(user_email)
                }
            }
        }catch (e:Exception){
        }

        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        share_btn.setOnClickListener {
            //Toast.makeText(this, "Feature will be available soon", Toast.LENGTH_SHORT).show()
            share()
        }

        category_btn.setOnClickListener {
            val i = Intent(this, Category::class.java)
            startActivity(i)
        }

        sign_out_btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        unlock_btn.setOnClickListener {
            Toast.makeText(this, "Feature will be available soon", Toast.LENGTH_SHORT).show()
        }

        favorite_btn.setOnClickListener {
            Toast.makeText(this, "Nothing is in your Favorite list", Toast.LENGTH_SHORT).show()
        }

        donate_btn.setOnClickListener {
            Toast.makeText(this, "Feature will be available soon", Toast.LENGTH_SHORT).show()
        }
    }


    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press once again to exit!", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun share(){
        val whatsAppAppId = "com.whatsapp"

        val packageManager = packageManager
        try {
            intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val sendText = "Hey there! I'm using *Digital Library* app. I'm enjoying an enormous collection of E-books. Download the app from Play store and start using it. Download link : https://play.google.com/store/apps/details?id=com.abhishek.digital_library"

            val info = packageManager.getPackageInfo(whatsAppAppId, PackageManager.GET_META_DATA)
            intent.`package` = whatsAppAppId
            intent.putExtra(Intent.EXTRA_TEXT, sendText)
            startActivity(Intent.createChooser(intent, "Share the app with : "))
        }catch (e:PackageManager.NameNotFoundException){
            Log.e("WhatsApp", "WhatsApp is not installed in this device")
        }
    }
}