package com.example.meetrip2.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityBoardWriteBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding
    private val TAG = BoardWriteActivity::class.java.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.writeBtn.setOnClickListener{
            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()

            Log.d(TAG, title)
            Log.d(TAG, content)

//            FBRef.boardRef
//                .child(FBAuth.getUid())
//                .child
//                .setValue(board)
        }
    }
}