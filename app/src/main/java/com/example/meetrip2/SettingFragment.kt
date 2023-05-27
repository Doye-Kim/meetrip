package com.example.meetrip2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.auth.IntroActivity
import com.example.meetrip2.databinding.FragmentSettingBinding
import com.example.meetrip2.recommend.RecommendContentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingFragment : Fragment(), MainActivity.onBackPressedListener {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
        binding.logout.setOnClickListener {
            auth.signOut()
            Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT).show()

            val intent = Intent(context, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        requireActivity().supportFragmentManager.popBackStack()
    }

}