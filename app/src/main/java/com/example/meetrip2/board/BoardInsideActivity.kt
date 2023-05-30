package com.example.meetrip2.board

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.meetrip2.R
import com.example.meetrip2.comment.CommentLVAdapter
import com.example.meetrip2.comment.CommentModel
import com.example.meetrip2.databinding.ActivityBoardInsideBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.toString()

    private lateinit var binding: ActivityBoardInsideBinding
    private lateinit var key: String
    private lateinit var myRef: DatabaseReference
    private var ccount = "0"
    private lateinit var commentLVadapter: CommentLVAdapter
    private val commentDataList = mutableListOf<CommentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)
        val database = Firebase.database
        myRef = database.getReference("comment")
        binding.menuBtn.setOnClickListener() {
            showDialog()
        }
        key = intent.getStringExtra("key").toString()

        Log.d("zlzl", key)
        countComment(key)
        getBoardData(key)
        getImageData(key)

        FBRef.boardRef.child(key).child("ccount").get().addOnSuccessListener {
            ccount = it.value.toString()
            binding.commentCount.text = ccount
        }

        binding.commentBtn.setOnClickListener {
            if (binding.commentArea.text.toString() == "") {
                Toast.makeText(this, "댓글을 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else{
                insertComment(key, ccount.toInt())
                try {
                    val intent = intent
                    finish() //현재 액티비티 종료 실시
                    overridePendingTransition(0, 0) //인텐트 애니메이션 없애기
                    startActivity(intent) //현재 액티비티 재실행 실시
                    overridePendingTransition(0, 0) //인텐트 애니메이션 없애기
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }

        commentLVadapter = CommentLVAdapter(commentDataList)
        binding.commentListView.adapter = commentLVadapter

        getCommentData(key)
    }

    private fun insertComment(key: String, ccount: Int){
        val time = FBAuth.getTime()
        FBRef.commentRef
            .child(key)
            .push()
            .setValue(CommentModel(binding.commentArea.text.toString(), time))
        Toast.makeText(this,"댓글 입력 완료", Toast.LENGTH_SHORT).show()
        binding.commentArea.setText("")
    }
    private fun countComment(key: String){
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for(dataModel in dataSnapshot.children){
                    if(dataModel.key.toString().equals(key)){
                        Log.d("key", "equals")
                        FBRef.boardRef.child(key).child("ccount").setValue(dataModel.childrenCount)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
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
    private fun getCommentData(key: String){
        val postListener = object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                commentDataList.clear()
                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(CommentModel::class.java)
                    commentDataList.add(item!!)
                }
                commentLVadapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        FBRef.commentRef.child(key).addValueEventListener(postListener)
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
