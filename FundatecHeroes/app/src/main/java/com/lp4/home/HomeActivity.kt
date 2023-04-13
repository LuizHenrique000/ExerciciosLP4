package com.lp4.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lp4.R
import com.lp4.adapter.MyAdapter
import com.lp4.adapter.UserListAdapter
import com.lp4.api.UserClient
import com.lp4.databinding.ActivityHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val scope = CoroutineScope(Dispatchers.IO)

        tabLayout = binding.tlHome
        viewPager = binding.vpHome

        tabLayout.addTab(tabLayout.newTab().setText("Heroi"))
        tabLayout.addTab(tabLayout.newTab().setText("Vilão"))
        tabLayout.addTab(tabLayout.newTab().setText("Anti Heroi"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        scope.launch {
            val apiClient = UserClient()
            val users = apiClient.getUsers()
            withContext(Dispatchers.Main) {

                val recyclerView: RecyclerView = binding.userList
                val adapter2 = UserListAdapter()
                recyclerView.adapter = adapter2

                adapter2.setItems(users)

            }
        }

    }

}