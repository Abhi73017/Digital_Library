package com.abhishek.digital_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userName = intent.getStringExtra("username") ?: return

        username_text.setText(userName)

        share_btn.setOnClickListener {
            Toast.makeText(this, "Feature will be available soon", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Feature will be available soon", Toast.LENGTH_SHORT).show()
        }

        donate_btn.setOnClickListener {
            Toast.makeText(this, "Feature will be available soon", Toast.LENGTH_SHORT).show()
        }
    }
}