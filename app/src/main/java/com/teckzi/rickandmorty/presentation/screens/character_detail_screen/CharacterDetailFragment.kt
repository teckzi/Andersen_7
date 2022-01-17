package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
        lifecycleScope.launch(Dispatchers.Main) {
            getCharacters()


            lifecycleScope.launch(Dispatchers.Main) {
                Log.d("TAG ROROROR", "${viewModel.selectedCharacter.value?.name}")
                with(binding){

                    characterDetailName.text = viewModel.selectedCharacter.value?.name ?: "RIRIRIR"
                }
            }
        }
    }

    private fun getCharacters() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getCharacter(args.characterId)
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

}