package com.example.meetrip2.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.MainActivity
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class joinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.joinBtn.setOnClickListener{

            var isGoToJoin = true

            val email = binding.emailArea.text.toString()
            val password1 = binding.passwordArea1.text.toString()
            val password2 = binding.passwordArea2.text.toString()

            // 값이 비어있는지 확인
            if(email.isEmpty()) {
                Toast.makeText(this,"이메일을 입력해주세요",Toast.LENGTH_LONG).show()
            }
            if(password1.isEmpty()) {
                Toast.makeText(this,"비밀번호를 입력해주세요",Toast.LENGTH_LONG).show()
            }
            if(password2.isEmpty()) {
                Toast.makeText(this,"비밀번호 확인을 입력해주세요",Toast.LENGTH_LONG).show()
            }

            // 비밀번호 2개가 동일
            if(!password1.equals(password2)){
                Toast.makeText(this,"비밀번호가 다릅니다",Toast.LENGTH_LONG).show()
            }

            //비밀번호 6자리 이상
            if(password1.length<6){
                Toast.makeText(this,"비밀번호 6자리 이상 입력해주세요",Toast.LENGTH_LONG).show()
            }

            if(isGoToJoin){

                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"회원가입성공 로그인해주세요",Toast.LENGTH_LONG).show()

                            val intent = Intent(this, introActivity :: class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                        } else {
                            Toast.makeText(this,"실패",Toast.LENGTH_LONG).show()
                        }
                    }
            }

        }

    }
}