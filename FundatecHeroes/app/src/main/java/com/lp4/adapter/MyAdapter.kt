package com.lp4.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lp4.fragments.AntiHeroi
import com.lp4.fragments.Heroi
import com.lp4.fragments.Vilao

internal class MyAdapter (var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                Heroi()
            }

            1 -> {
                Vilao()
            }

            2 -> {
                AntiHeroi()
            }

            else -> getItem(position)

        }
    }

    override fun getCount(): Int {
       return totalTabs
    }

}