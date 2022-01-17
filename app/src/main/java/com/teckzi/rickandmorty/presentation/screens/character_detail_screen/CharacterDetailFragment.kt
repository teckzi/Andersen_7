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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args by navArgs<CharacterDetailFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getCharacter(args.characterId)
            viewModel.selectedCharacter.collectLatest {
                it?.let { it ->
                    loadCharacterData(
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        gender = it.gender,
                        image = it.image
                    )
                    viewModel.getOrigin(it.origin)
                    viewModel.getLocation(it.location)
                }
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedOrigin.collectLatest {
                binding.characterDetailOrigin.text = it ?: "unknown"
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedLocation.collectLatest {
                binding.characterDetailLocation.text = it ?: "unknown"
            }
        }
    }

    private fun loadCharacterData(
        name: String,
        status: String,
        species: String,
        gender: String,
        image: String
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
        //adapter = DetailsEpisodeAdapter(requireContext(),episodeList)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
        val popBack = menu.findItem(R.id.popBack)
        popBack.setOnMenuItemClickListener {
            findNavController().popBackStack()
        }
    }
}