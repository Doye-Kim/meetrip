package com.example.meetrip2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meetrip2.auth.IntroActivity
import com.example.meetrip2.contentsList.BookmarkRVAdapter
import com.example.meetrip2.contentsList.ContentsModel
import com.example.meetrip2.databinding.FragmentMypageBinding
import com.example.meetrip2.utils.FBAuth
import com.example.meetrip2.utils.FBRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MypageFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private val TAG = MypageFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        auth = Firebase.auth
        val v: View = inflater.inflate(R.layout.fragment_mypage, container, false)
        v.findViewById<ImageView>(R.id.menuBtn).setOnClickListener {
            v.findViewById<LinearLayout>(R.id.menuBar).visibility = View.VISIBLE
        }
        v.findViewById<Button>(R.id.logoutBtn).setOnClickListener {
            auth.signOut()
            val intent = Intent(context, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        return v
    }
}