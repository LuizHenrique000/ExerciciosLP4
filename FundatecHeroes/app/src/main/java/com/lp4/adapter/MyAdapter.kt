package com.lp4.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lp4.domain.CategoryType
import com.lp4.fragment.CharacterFragment

internal class MyAdapter (var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                CharacterFragment.newInstance(CategoryType.HERO)
            }

            1 -> {
                CharacterFragment.newInstance(CategoryType.VILLAIN)
            }

            2 -> {
                CharacterFragment.newInstance(CategoryType.ANTIHERO)
            }

            else -> getItem(position)

        }
    }

    override fun getCount(): Int {
       return totalTabs
    }

}