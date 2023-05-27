package com.example.meetrip2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.meetrip2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var homFragment: HomeFragment? = null
    var recFragment: RecommendFragment? = null
    var mypFragment: MypageFragment? = null
    var setFragment: SettingFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.settingBtn.setOnClickListener {
            Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show()
            binding.toolBar.visibility = View.GONE
            supportFragmentManager.beginTransaction().replace(R.id.container, setFragment!!)
                .commit()
        }


        // 네비게이션 메뉴 아이템에 클릭 속성 부여
        homFragment = HomeFragment()
        recFragment = RecommendFragment()
        mypFragment = MypageFragment()
        setFragment = SettingFragment()

        supportFragmentManager.beginTransaction().replace(R.id.container, homFragment!!).commit()
        val bottom_menu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottom_menu.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    binding.toolBar.visibility = View.GONE
                    supportFragmentManager.beginTransaction().replace(R.id.container, homFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.recommend -> {
                    binding.toolBar.visibility = View.GONE
                    supportFragmentManager.beginTransaction().replace(R.id.container, recFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.mypage -> {
                    binding.toolBar.visibility = View.VISIBLE
                    supportFragmentManager.beginTransaction().replace(R.id.container, mypFragment!!)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
       }
    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }
    }
}