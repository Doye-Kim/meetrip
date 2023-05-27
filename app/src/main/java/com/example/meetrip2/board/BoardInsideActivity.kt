package com.example.meetrip2.board

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.meetrip2.R
import com.example.meetrip2.databinding.ActivityBoardInsideBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.ktx.storage

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.toString()

    private lateinit var binding : ActivityBoardInsideBinding
    private lateinit var key : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)

        binding.menuBtn.setOnClickListener(){
            showDialog()
        }

        key = intent.getStringExtra("key").toString()
        getBoardData(key)
        getImageData(key)
    }

    private fun showDialog(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.board_write_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("게시글 수정/삭제")

        val alertDialog = mBuilder.show()
        alertDialog.findViewById<Button>(R.id.editBtn)?.setOnClickListener(){
            val intent = Intent(this, BoardEditActivity::class.java)
            intent.putExtra("key", key)
            startActivity(intent)
        }
        alertDialog.findViewById<Button>(R.id.deleteBtn)?.setOnClickListener(){
            FBRef.boardRef.child(key).removeValue()
            finish()
        }
    }
    private fun getBoardData(key: String){
        val postListener = object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                try{
                    val dataModel = dataSnapshot.getValue(BoardModel::class.java)
                    binding.titleArea.text = dataModel!!.title
                    binding.contentArea.text = dataModel!!.content
                    binding.timeArea.text = dataModel!!.time

                    val myUid = FBAuth.getUid()
                    val writerUid = dataModel.uid

                    if(myUid.equals(writerUid)){
                        binding.menuBtn.visibility = View.VISIBLE
                    }
                }catch(e : Exception){
                    Log.d(TAG, "삭제 완료")
                }
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
}