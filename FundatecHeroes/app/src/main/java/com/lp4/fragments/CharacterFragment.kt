package com.lp4.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lp4.R
import com.lp4.adapter.HeroListAdapter
import com.lp4.api.hero.HeroClient
import com.lp4.databinding.FragmentHeroiBinding
import com.lp4.domain.CategoryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_heroi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            val apiClient = HeroClient()
            val heros = apiClient.getUsers()
            withContext(Dispatchers.Main) {

                val binding = FragmentHeroiBinding.bind(view)
                val recyclerView: RecyclerView = binding.userList
                val adapter2 = HeroListAdapter()
                recyclerView.adapter = adapter2
                adapter2.setItems(heros)
            }
        }

    }

    companion object {

        private lateinit var categoryType: CategoryType

        fun newInstance(type: CategoryType): Fragment {
            categoryType = type
            return CharacterFragment()
        }

    }

}