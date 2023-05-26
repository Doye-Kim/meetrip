package com.example.meetrip2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.board.BoardInsideActivity
import com.example.meetrip2.board.BoardListLVAdapter
import com.example.meetrip2.board.BoardModel
import com.example.meetrip2.board.BoardWriteActivity
import com.example.meetrip2.databinding.ActivityCommunityBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class CommunityActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCommunityBinding
    private val boardDataList = mutableListOf<BoardModel>()
    private val TAG = CommunityActivity::class.java.toString()
    private val boardKeyList = mutableListOf<String>()
    private lateinit var boardLVadapter : BoardListLVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_community)

        boardLVadapter = BoardListLVAdapter(boardDataList)
        binding.boardListView.adapter = boardLVadapter

        binding.boardListView.setOnItemClickListener{ parent, view, position, id ->
//            val intent = Intent(this, BoardInsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

            val intent = Intent(this, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }

        binding.writeBtn.setOnClickListener{
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)
        }
        getFBBoardData()
    }

    private fun getFBBoardData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                boardDataList.clear()
                boardKeyList.clear()
                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())

                }
                boardKeyList.reverse()
                boardDataList.reverse()
                boardLVadapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Failed to read value.", error.toException())
            }
        }
        FBRef.boardRef.addValueEventListener(postListener)
    }
}