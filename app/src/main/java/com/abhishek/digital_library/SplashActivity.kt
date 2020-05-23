@file:Suppress("DEPRECATION")

package com.abhishek.digital_library

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.android.play.core.appupdate.*
import android.view.View.VISIBLE
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private val MY_REQUEST_CODE = 9465
    lateinit var topAnim : Animation

    override fun onStart() {
        super.onStart()

        if (isOnline()){
            updateApp()
        }else
        {
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Internet Connection is not Available.")
                .setCancelable(false)
                .setPositiveButton("OK", { dialog, id ->
                    System.exit(-1)
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Error")
            alert.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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
                        if(isOnline()){
                            auth = FirebaseAuth.getInstance()
                            val user = auth.currentUser
                            if (user!=null){
                                val i = Intent(this, Dashboard::class.java)
                                startActivity(i)
                                finish()
                            }
                            else{
                                val i = Intent(this, LoginActivity::class.java)
                                startActivity(i)
                                finish()
                            }
                        }
                    }, 500)
                }, 5000
            )
    }

    private fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    private fun updateApp() {

        val appUpdateManager = AppUpdateManagerFactory.create(this)

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    MY_REQUEST_CODE)
            }
        }
    }
}