package com.example.meetrip2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.board.BoardWriteActivity
import com.example.meetrip2.databinding.ActivityCommunityBinding


class CommunityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_community)
        binding.writeBtn.setOnClickListener{
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }
    }
}