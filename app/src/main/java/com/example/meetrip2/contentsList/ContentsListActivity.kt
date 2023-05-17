package com.example.meetrip2.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meetrip2.R
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentsListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference
    lateinit var rvAdapter: ContentsRVAdapter

    val bookmarkIdList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentsModel>()
        val database = Firebase.database
        myRef = database.getReference("contents2")
        val itemKeyList = ArrayList<String>()

        rvAdapter = ContentsRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)


//        myRef.push().setValue(ContentsModel("다대포해수욕장", "https://postfiles.pstatic.net/MjAyMzA1MDFfNTIg/MDAxNjgyOTE5MDAwMTUy.yqy-vhuGMwThPTo9kxO7u9UX3MIvJ4oSaeafQFmD8kYg.vZdvf8Pak8GteB8rPM-Zl8UJ0XUfZJcuWYurobNYtrgg.JPEG.kmdy125/IMG-3694.jpg?type=w580"))
//        myRef.push().setValue(ContentsModel("화명생태공원", "https://postfiles.pstatic.net/MjAyMzA1MDFfMjU0/MDAxNjgyOTE4OTY3NzYw.SkmSgLuo9StXgoRSWcyQwqegGT-WAJKKdZ-LYtcaXTwg.tGxjGeLtjea_q7sFU6gj4DVRncY_zIxF-7I31_HeOCUg.JPEG.kmdy125/IMG_9154.jpg?type=w580"))


        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(dataModel in dataSnapshot.children){
                    Log.d("ContentsListActivity", dataSnapshot.toString())
                    val item = dataModel.getValue(ContentsModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged() //데이터를 불러오는 동안 rv가 생성되면 rv에 데이터가 들어가지 않기 때문에 여기서 리프레시 함
                Log.d("ContentsListActivity", items.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ContentsListActivity", "Failed to read value.", error.toException())
            }
        })
        val rv : RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this, 2)

        getBookmarkData()
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookmarkIdList.clear()

                for(dataModel in dataSnapshot.children){
                    bookmarkIdList.add(dataModel.key.toString())
                }
                Log.d("ContentsListActivitybookmarklist", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("ContentsListActivity", "Failed to read value.", error.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }
}


//        myRef.push().setValue(ContentsModel("imageurl2", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png"))
//        myRef.push().setValue(ContentsModel("imageurl3", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png"))
//          items.add(ContentsModel("imageurl2", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFtY3t%2Fbtq65q6P4Zr%2FWe64GM8KzHAlGE3xQ2nDjk%2Fimg.png"))
//          items.add(ContentsModel("imageurl3", "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbdIKDG%2Fbtq64M96JFa%2FKcJiYgKuwKuP3fIyviXm90%2Fimg.png"))
