package com.example.meetrip2.contentsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meetrip2.R
import com.example.meetrip2.databinding.FragmentMypageBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentMypageBinding
    private val TAG = BookmarkFragment::class.java.simpleName

    val bookmarkIDList = mutableListOf<String>()
    val items = ArrayList<ContentsModel>()
    val itemKeyList = ArrayList<String>()

    lateinit var rvAdapter: BookmarkRVAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)

        // 북마크한 정보 가져옴
        getBookmarkData()
        // 컨텐츠 중 북마크 한 것만 가져옴
        rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIDList)

        val rv : RecyclerView = binding.bookmarkRV
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)

        return binding.root
    }
    private fun getContentsData() {
        val postListner = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())
                    val item = dataModel.getValue(ContentsModel::class.java)
                    if(bookmarkIDList.contains(dataModel.key.toString())){
                        items.add(item!!)
                        itemKeyList.add(dataModel.key.toString())
                    }
                    else continue
                }
                rvAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        FBRef.list.addValueEventListener(postListner)
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                for(dataModel in dataSnapshot.children){
                    Log.e(TAG, dataModel.toString())
                    bookmarkIDList.add(dataModel.key.toString())
                }
                //전체 카테고리에 있는 컨텐츠 데이터 다 가져옴
                getContentsData()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }
}