package com.abhishek.digital_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    lateinit var topAnim : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        topAnim = AnimationUtils.loadAnimation(this, R.anim.topanim)


        imageView.animation = topAnim
        dg_text.animation = topAnim

        Handler().postDelayed(
            {
                Handler().postDelayed({
                    progressbar.visibility = VISIBLE
                }, 500)
            }, 3000
        )

        Handler().postDelayed(
            {
                Handler().postDelayed({
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }, 500)
            }, 5000
        )
    }
}
