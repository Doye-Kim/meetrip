package com.example.meetrip2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.MainActivity
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        auth = Firebase.auth
        binding.loginBtn.setOnClickListener{
            val email = binding.emailArea.text.toString()
            val pwd = binding.pwdArea.text.toString()

            auth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        finish()
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"login success", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,"login fail", Toast.LENGTH_LONG).show()

                    }
                }
            }
    }
}