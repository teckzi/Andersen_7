package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.teckzi.rickandmorty.domain.model.EpisodeModel
import com.teckzi.rickandmorty.presentation.adapters.CharacterDetailsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private lateinit var episodeAdapter: CharacterDetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedCharacter.collectLatest { character ->
                character?.let { it ->
                    loadCharacterData(
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        gender = it.gender,
                        image = it.image,
                        origin = it.origin,
                        location = it.location
                    )
                }
            }
        }
        getEpisodes()
        clickOrigin()
        navigateToAllEpisodes()
    }

    private fun loadCharacterData(
        name: String,
        status: String,
        species: String,
        gender: String,
        image: String,
        origin: String,
        location: String
    ) {

        binding.characterDetailImage.load(image) {
            transformations(CircleCropTransformation())
        }
        binding.backgroundImage.load(image) {
            transformations(BlurTransformation(requireContext(), 6f))
        }
        binding.characterDetailName.text = name
        binding.characterDetailStatus.text = status
        when (status) {
            "Alive" -> binding.characterDetailStatus.setTextColor(Color.parseColor("#2EED0C"))
            "Dead" -> binding.characterDetailStatus.setTextColor(Color.parseColor("#FF0000"))
            "unknown" -> binding.characterDetailStatus.setTextColor(Color.parseColor("#C4FFFFFF"))
        }
        binding.characterDetailGender.text = gender
        binding.characterDetailSpecies.text = species
        binding.characterDetailOrigin.text = origin
        binding.characterDetailLocation.text = location
    }

    private fun initRecyclerView(episodeList: List<EpisodeModel>) {
        episodeAdapter = CharacterDetailsAdapter(requireContext(), episodeList)
        binding.characterDetailEpisodes.layoutManager = LinearLayoutManager(requireContext())
        binding.characterDetailEpisodes.adapter = episodeAdapter
    }

    private fun getEpisodes() {
        lifecycleScope.launch {
            viewModel.episodeList.collectLatest {
                initRecyclerView(it as List<EpisodeModel>)
            }
        }
    }

    private fun navigateToAllEpisodes() {
        binding.allEpisodes.setOnClickListener {
            findNavController().navigate(R.id.action_characterDetailScreen_to_episodeFragment)
        }
    }

    private fun clickOrigin() {
        lifecycleScope.launch {
            viewModel.characterOrigin.collectLatest { locationModel ->
                val id = locationModel?.id
                if (id != null) if (binding.characterDetailOrigin.text.toString() != "unknown") binding.originButton.setOnClickListener {
                    val action =
                        CharacterDetailFragmentDirections.actionCharacterDetailScreenToLocationDetailFragment(
                            id
                        )
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.characterLocation.collectLatest { locationModel ->
                val id = locationModel?.id
                if (id != null) if (binding.characterDetailLocation.text.toString() != "unknown") binding.locationButton.setOnClickListener {
                    val action =
                        CharacterDetailFragmentDirections.actionCharacterDetailScreenToLocationDetailFragment(
                            id
                        )
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        val popBack = menu.findItem(R.id.popBack)
        popBack.setOnMenuItemClickListener {
            findNavController().popBackStack()
        }
    }
}