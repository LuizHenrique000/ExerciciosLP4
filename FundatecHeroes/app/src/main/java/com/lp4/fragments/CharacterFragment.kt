package com.lp4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lp4.R
import com.lp4.domain.CategoryType

class CharacterFragment : Fragment() {

    //Essa variavel será utilizada para fazer o filtro de personagens

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heroi, container, false)
    }

    companion object {

        lateinit var categoryType: CategoryType

        fun newInstance(type: CategoryType): Fragment {
            categoryType = type
            return CharacterFragment()
        }


    }

}