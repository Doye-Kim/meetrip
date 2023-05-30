package com.example.meetrip2.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityBoardEditBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardEditActivity : AppCompatActivity() {

    private lateinit var key : String
    private lateinit var binding: ActivityBoardEditBinding
    private val TAG = BoardEditActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        key = intent.getStringExtra("key").toString()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)
        getBoardData(key)
        getImageData(key)

        binding.editBtn.setOnClickListener{
            editBoardData(key)
        }
        binding.imageBtn.setOnClickListener{
//            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//            startActivityForResult(gallery, 100)
//            isImageUpload = true
        }
    }
    private fun getBoardData(key: String){
        val postListener = object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    binding.titleArea.setText(dataModel!!.title)
                    binding.contentArea.setText(dataModel!!.content)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        FBRef.boardRef.child(key).addValueEventListener(postListener)

    }
    private fun getImageData(key: String){
        //이미지 경로 설정
        val storageReference = Firebase.storage.reference.child(key + ".jpeg")
        val imageViewFromFB = binding.imageArea

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Success")
                imageViewFromFB.visibility = View.VISIBLE
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            } else {
                Log.d(TAG, "Fail")
            }
        })
    }
    private fun editBoardData(key: String){
        val title = binding.titleArea.text.toString()
        val content = binding.contentArea.text.toString()
        val uid = FBAuth.getUid()
        val time = FBAuth.getTime() + "(수정됨)"

        if(title == ""){
            Toast.makeText(this,"제목을 입력해 주세요", Toast.LENGTH_LONG).show()
        }
        else if(content == ""){
            Toast.makeText(this,"내용을 입력해 주세요", Toast.LENGTH_LONG).show()
        }
        else{
            FBRef.boardRef
                .child(key)
                .setValue(BoardModel(title, content, uid, time))
            finish()
        }
    }
}