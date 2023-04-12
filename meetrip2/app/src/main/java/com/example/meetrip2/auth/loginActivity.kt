package com.example.meetrip2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.MainActivity
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityJoinBinding
import com.example.meetrip2.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginBtn.setOnClickListener{

            val email = binding.emailArea.text.toString()
            val password = binding.passwordArea.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(this,"로그인", Toast.LENGTH_LONG).show()

                        val intent = Intent(this, MainActivity :: class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)

                    } else {

                        Toast.makeText(this,"입력하신 정보가 틀렸습니다",Toast.LENGTH_LONG).show()

                    }
                }

        }

    }
}