package com.lp4.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lp4.R
import com.lp4.character.NewCharacter
import com.lp4.databinding.FragmentAntiHeroiBinding

class AntiHeroi : Fragment() {

    private lateinit var binding: FragmentAntiHeroiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_anti_heroi, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAntiHeroiBinding.bind(view)

        binding.btAdicionarAntiHeroi.setOnClickListener(){
            requireActivity().run {
                startActivity(Intent(this, NewCharacter::class.java))
                finish()
            }
        }

    }

}