package com.teckzi.rickandmorty.presentation.screens.character_screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterBinding
import com.teckzi.rickandmorty.presentation.adapters.CharacterAdapter
import com.teckzi.rickandmorty.presentation.adapters.LoaderStateAdapter
import com.teckzi.rickandmorty.util.Constants.CHARACTER_TYPE
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_CHARACTER
import com.teckzi.rickandmorty.util.Constants.FILTER_TYPE_ARGUMENT_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character), SearchView.OnQueryTextListener,
    SwipyRefreshLayout.OnRefreshListener {
    private val viewModel by viewModels<CharacterViewModel>()
    private val binding by viewBinding(FragmentCharacterBinding::bind)
    private lateinit var characterAdapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initRecyclerView()
        getCharacters()
        getFilterResult()
        filterButton()
        binding.swipeRefreshLayout.setOnRefreshListener(this)
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
            binding.characterMainRecyclerView.isVisible = state.refresh != LoadState.Loading
            binding.errorMessage.isVisible = state.e
            binding.errorImage.isVisible
        }
    }

    private fun getCharacters() {
        lifecycleScope.launch {
            viewModel.getAllCharacters.collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun filterButton() {
        binding.filterFloatingButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_characterFragment_to_bottomSheet,
                bundleOf(FILTER_TYPE_ARGUMENT_KEY to CHARACTER_TYPE)
            )
        }
    }

    private fun getFilterResult() {
        arguments?.getString(FILTER_RETURN_BACK_TO_CHARACTER)?.let {
            lifecycleScope.launch(context = Dispatchers.Main) {
                val (status, species, type, gender) = it.split(",")
                searchCharacter(status = status, species = species, type = type, gender = gender)
            }
        }
    }

    private fun searchCharacter(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null
    ) {
        lifecycleScope.launch {
            viewModel.searchCharacter(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender
            )
            viewModel.searchCharacter.collectLatest { pagingData ->
                characterAdapter.submitData(pagingData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView
        searchView?.queryHint = "Search character..."
        searchView?.setOnQueryTextListener(this)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as? SearchView
        searchView?.isIconified = true
        searchView?.isIconified = true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) searchCharacter(name = query)
        return true
    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        getCharacters()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    companion object {
        private const val TAG = "TAG CharacterFragment"
    }
}