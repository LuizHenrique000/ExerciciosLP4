package com.lp4.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.lp4.R
import com.lp4.adapter.PersonagemListAdapter
import com.lp4.api.CharacterClient
import com.lp4.databinding.FragmentHeroiBinding
import com.lp4.domain.CategoryType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var fragmentHeroiBinding: FragmentHeroiBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            val sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
            val apiClient = CharacterClient()
            val id = sharedPreferences.getInt("id", 0)
            val personagens = apiClient.getCharacter(id.toString())
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                val recyclerView: RecyclerView = fragmentHeroiBinding.userList
                val adapter = PersonagemListAdapter()
                recyclerView.adapter = adapter
                if (personagens != null) {
                    adapter.setItems(personagens)
                }
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHeroiBinding.inflate(inflater, container, false)
        fragmentHeroiBinding = binding
        return binding.root
    }

    companion object {

        lateinit var categoryType: CategoryType

        fun newInstance(type: CategoryType): Fragment {
            categoryType = type
            return CharacterFragment()
        }

    }
}
