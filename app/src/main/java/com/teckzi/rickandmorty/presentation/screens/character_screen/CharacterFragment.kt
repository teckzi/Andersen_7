package com.teckzi.rickandmorty.presentation.screens.character_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterBinding
import com.teckzi.rickandmorty.presentation.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment:Fragment(R.layout.fragment_character){

    private val viewModel by viewModels<CharacterViewModel>()
    private val binding by viewBinding(FragmentCharacterBinding::bind)
    private lateinit var characterAdapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initRecyclerView() {
        characterAdapter = CharacterAdapter(requireContext())
        binding.characterMainRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.characterMainRecyclerView.adapter = characterAdapter

//                .withLoadStateHeaderAndFooter(
//                header = LoaderStateAdapter(),
//                footer = LoaderStateAdapter()
//            )
//        adapter.addLoadStateListener { state: CombinedLoadStates ->
//            binding.characterMainRecyclerView.isVisible = state.refresh != LoadState.Loading
//            binding.characterProgressBar.isVisible = state.refresh == LoadState.Loading
//        }

    }

}