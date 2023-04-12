package com.example.meetrip2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityIntroBinding

class introActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
 //       setContentView(R.layout.activity_intro)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }

        binding.joinBtn.setOnClickListener{
            val intent = Intent(this, joinActivity::class.java)
            startActivity(intent)
        }

    }
}