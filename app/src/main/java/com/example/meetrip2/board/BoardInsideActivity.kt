package com.example.meetrip2.board

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityBoardInsideBinding
import com.example.meetrip2.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.toString()

    private lateinit var binding : ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

//        val title = intent.getStringExtra("title").toString()
//        val content = intent.getStringExtra("content").toString()
//        val time = intent.getStringExtra("time").toString()
//
//        binding.titleArea.text = title
//        binding.timeArea.text = time
//        binding.contentArea.text = content

        val key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)
    }

    private fun getBoardData(key: String){
        val postListener = object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                binding.titleArea.text = dataModel!!.title
                binding.contentArea.text = dataModel!!.content
                binding.timeArea.text = dataModel!!.time
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)

    }
    private fun getImageData(key: String){
        //이미지 경로 설정
        val storageReference = Firebase.storage.reference.child(key + ".jpeg")

        val imageViewFromFB = binding.getImageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener{  task ->
            if(task.isSuccessful){
                imageViewFromFB.visibility = View.VISIBLE
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            }
            else{

            }

        })
    }
}