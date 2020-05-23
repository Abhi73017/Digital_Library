@file:Suppress("DEPRECATION")

package com.abhishek.digital_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import android.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.GoogleAuthProvider
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var auth: FirebaseAuth

    companion object {
        private val PERMISSION_CODE = 9999
    }


    lateinit var mGoogleApiClient: GoogleApiClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var alertdialog: AlertDialog


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PERMISSION_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result!!.isSuccess) {
                val account = result.signInAccount
                val idToken = account!!.idToken
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                firebaseAuthWithGoogle(credential)
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            } else {
                Log.d("LOGIN_ERROR", "Login Unsuccessful")
                Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun firebaseAuthWithGoogle(credential: AuthCredential) {

        firebaseAuth!!.signInWithCredential(credential!!)
            .addOnSuccessListener { authResult ->

                val logged_activity = Intent(this@LoginActivity, Dashboard::class.java)
                alertdialog= SpotsDialog.Builder()
                    .setContext(this)
                    .setTheme(R.style.Custom1)
                    .build()
                    .apply {show() }

                startActivity(logged_activity)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        register_text.setOnClickListener {
            email_register_btn.visibility = View.VISIBLE
            login_text.visibility = View.VISIBLE
            register_text.visibility = View.GONE
            email_login_btn.visibility = View.GONE
        }

        mail_login_btn.setOnClickListener {
            mail_login_btn.visibility = View.GONE
            google_login_btn.visibility = View.GONE
            fb_login_btn.visibility = View.GONE
            phone_login_btn.visibility = View.GONE
            edit_email_field_login.visibility = View.VISIBLE
            edit_pass_field_login.visibility = View.VISIBLE
            email_login_btn.visibility = View.VISIBLE
            back_text.visibility = View.VISIBLE
            register_text.visibility = View.VISIBLE
        }

        back_text.setOnClickListener {
            mail_login_btn.visibility = View.VISIBLE
            google_login_btn.visibility = View.VISIBLE
            fb_login_btn.visibility = View.VISIBLE
            phone_login_btn.visibility = View.VISIBLE
            edit_email_field_login.visibility = View.GONE
            edit_pass_field_login.visibility = View.GONE
            email_login_btn.visibility = View.GONE
            back_text.visibility = View.GONE
            register_text.visibility = View.GONE
            login_text.visibility = View.GONE
            email_register_btn.visibility  = View.GONE
        }

        login_text.setOnClickListener {
            email_register_btn.visibility = View.GONE
            login_text.visibility = View.GONE
            register_text.visibility = View.VISIBLE
            email_login_btn.visibility = View.VISIBLE
        }

        email_login_btn.setOnClickListener {
            if(validateForm()){
                val email = edit_email_field_login.text.toString()
                val pass = edit_pass_field_login.text.toString()

                alertdialog = SpotsDialog.Builder()
                    .setContext(this)
                    .setTheme(R.style.Custom2)
                    .build()
                    .apply { show() }

                signinWithEmail(email, pass)
            }
        }

        email_register_btn.setOnClickListener {
            if (validateForm()) {
                val email = edit_email_field_login.text.toString()
                val pass = edit_pass_field_login.text.toString()

                alertdialog = SpotsDialog.Builder()
                    .setContext(this)
                    .setTheme(R.style.Custom2)
                    .build()
                    .apply { show() }

                register(email, pass)
            }
        }

            configureGoogleClient()

            firebaseAuth = FirebaseAuth.getInstance()

            google_login_btn.setOnClickListener {
                Toast.makeText(this, "Sign in process started", Toast.LENGTH_SHORT).show()
                SignInWithGoogle()
            }

        fb_login_btn.setOnClickListener {
            Toast.makeText(this,"This login feature will be available in the next version.", Toast.LENGTH_SHORT).show()
        }

        phone_login_btn.setOnClickListener {
            Toast.makeText(this,"This login feature will be available in the next version.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun SignInWithGoogle() {
        val intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(
            intent,
            PERMISSION_CODE
        )

    }

    private fun configureGoogleClient() {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, options)
            .build()
        mGoogleApiClient.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this, "" + p0.errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = edit_email_field_login.text.toString()
        if (TextUtils.isEmpty(email)) {
            edit_email_field_login.error = "Required."
            valid = false
        } else {
            edit_email_field_login.error = null
        }

        val password = edit_pass_field_login.text.toString()
        if (TextUtils.isEmpty(password)) {
            edit_pass_field_login.error = "Required."
            valid = false
        } else {
            edit_pass_field_login.error = null
        }

        return valid
    }

    private fun signinWithEmail(email : String, pass: String){
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    alertdialog.cancel()

                    alertdialog = SpotsDialog.Builder()
                        .setContext(this)
                        .setTheme(R.style.Custom1)
                        .build()
                        .apply { show() }

                    val i = Intent(this, Dashboard::class.java)
                    startActivity(i)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun register(email: String, pass: String){
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    alertdialog.cancel()

                    alertdialog = SpotsDialog.Builder()
                        .setContext(this)
                        .setTheme(R.style.Custom1)
                        .build()
                        .apply { show() }

                    val i = Intent(this, Dashboard::class.java)
                    startActivity(i)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}