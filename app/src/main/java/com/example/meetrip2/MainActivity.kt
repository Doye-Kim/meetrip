package com.example.meetrip2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.meetrip2.contentsList.BookmarkFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var homFragment: HomeFragment? = null
    var recFragment: RecommendFragment? = null
    var mypFragment: MypageFragment? = null
    var bmFragment: BookmarkFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homFragment = HomeFragment()
        recFragment = RecommendFragment()
        mypFragment = MypageFragment()
        bmFragment = BookmarkFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, homFragment!!).commit()
        val bottom_menu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottom_menu.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, homFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.recommend -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, recFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.mypage -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, mypFragment!!)
                        .commit()
                    supportFragmentManager.beginTransaction().replace(R.id.container, bmFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}