package com.example.meetrip2.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityBoardWriteBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding
    private val TAG = BoardWriteActivity::class.java.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)
        var isImageUpload = false

        binding.writeBtn.setOnClickListener{
            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            if(title == ""){
                Toast.makeText(this,"제목을 입력해 주세요", Toast.LENGTH_LONG).show()
            }
            else if(content == ""){
                Toast.makeText(this,"내용을 입력해 주세요", Toast.LENGTH_LONG).show()
            }
            else{
                val key = FBRef.boardRef.push().key.toString()
                FBRef.boardRef
                    .child(key)
                    .setValue(BoardModel(title, content, uid, time))

                Toast.makeText(this, "입력 완료", Toast.LENGTH_SHORT).show()
                if(isImageUpload == true) {
                    imageUpload(key)
                }
                finish()
            }
        }
        binding.imageBtn.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
            isImageUpload = true

        }
    }
    private fun imageUpload(key: String){
        val storage = Firebase.storage
        val storageRef = storage.reference
        val writingRef = storageRef.child(key+".jpeg")

        val imageView = binding.imageArea
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = writingRef.putBytes(data)
        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100){
            binding.imageArea.setImageURI(data?.data)

        }
    }
}