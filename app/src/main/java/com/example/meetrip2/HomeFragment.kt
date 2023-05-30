package com.example.meetrip2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.meetrip2.board.BoardEditActivity
import com.example.meetrip2.board.BoardModel
import com.example.meetrip2.contentsList.ContentsListActivity
import com.example.meetrip2.contentsList.ContentsModel
import com.example.meetrip2.databinding.FragmentHomeBinding
import com.example.meetrip2.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.thread
import java.lang.Thread as Thread1


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    lateinit var myRef : DatabaseReference
    val items = ArrayList<BoardModel>()
    private val TAG = HomeFragment::class.java.simpleName



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val database = Firebase.database
        myRef = database.getReference("board")

        getBoardData()

        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        ft.detach(this).attach(this).commit()

        binding.ctView.setOnClickListener{
            val intent = Intent(context, ContentsListActivity::class.java)
            startActivity(intent)
        }
        binding.cmView.setOnClickListener{
            val intent = Intent(context, CommunityActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    private fun getBoardData() {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val thread0 = Thread1 {
                        for (dataModel in dataSnapshot.children) {
                            val item = dataModel.getValue(BoardModel::class.java)
                            items.add(item!!)
                        }
                    }
                    thread0.start()
                    thread0.join()
                    bindingData()
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "boardError")
                }
            })
    }
    private fun bindingData() {
        binding.board0.visibility = View.VISIBLE
        binding.board0.text = items[items.size - 1].title
        if (items.size >= 2) {
            binding.board1.visibility = View.VISIBLE
            binding.board1.text = items[items.size - 2].title
        }
    }
}