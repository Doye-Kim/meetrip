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

class JoinActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_join)

        auth = Firebase.auth

        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        binding.joinBtn.setOnClickListener{

            var isGoToJoin = true

            val email = binding.emailArea.text.toString()
            val pwd1 = binding.pwdArea1.text.toString()
            val pwd2 = binding.pwdArea2.text.toString()

            //값 비어 있는지 확인
            if(email.isEmpty()){
                Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(pwd1.isEmpty()){
            Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(pwd2.isEmpty()){
                Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            //비밀번호 일치하는지 확인
            if(!pwd1.equals(pwd2)){
                Toast.makeText(this, "비밀번호를 똑같이 입력해 주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            //비밀번호 6자 이상인지
            if(pwd1.length < 6){
                Toast.makeText(this, "비밀번호를 6자리 이상 입력해 주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(isGoToJoin){
                auth.createUserWithEmailAndPassword(email, pwd1)
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            finish()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                        }

                    }
            }
        }

    }
}