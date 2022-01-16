package com.teckzi.rickandmorty.presentation.screens.character_screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterBinding
import com.teckzi.rickandmorty.presentation.adapters.CharacterAdapter
import com.teckzi.rickandmorty.presentation.adapters.LoaderStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {

    private val viewModel by viewModels<CharacterViewModel>()
    private val binding by viewBinding(FragmentCharacterBinding::bind)
    private lateinit var characterAdapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getCharacters()
    }

    private fun initRecyclerView() {
        characterAdapter = CharacterAdapter(requireContext())
        binding.characterMainRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.characterMainRecyclerView.adapter = characterAdapter.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter(),
            footer = LoaderStateAdapter()
        )
        characterAdapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.characterMainRecyclerView.isVisible = state.refresh != LoadState.Loading
            binding.characterProgressBar.isVisible = state.refresh == LoadState.Loading
        }
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            viewModel.getAllCharacters.collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }
}