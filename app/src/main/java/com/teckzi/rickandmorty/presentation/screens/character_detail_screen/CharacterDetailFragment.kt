package com.teckzi.rickandmorty.presentation.screens.character_detail_screen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentCharacterDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "TAG CharacterDetailFragment"
class CharacterDetailFragment : Fragment() {
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private var binding: FragmentCharacterDetailBinding? = null
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(layoutInflater)
        val view = binding?.root
        //lifecycleScope.launch {
//            viewModel.selectedCharacter.collectLatest {
//                loadCharacterData(it!!.name,it.status,it.species,it.gender,it.image)
//            }
//        }
        return view
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

        binding?.characterDetailImage?.load(image) {
            transformations(CircleCropTransformation())
        }
        binding?.backgroundImage?.load(image) {
            transformations(BlurTransformation(requireContext(), 6f))
        }
        binding?.characterDetailName?.text = name
        binding?.characterDetailStatus?.text = status
        when (status) {
            "Alive" -> binding?.characterDetailStatus?.setTextColor(Color.parseColor("#2EED0C"))
            "Dead" -> binding?.characterDetailStatus?.setTextColor(Color.parseColor("#FF0000"))
            "unknown" -> binding?.characterDetailStatus?.setTextColor(Color.parseColor("#C4FFFFFF"))
        }
        binding?.characterDetailGender?.text = gender
        binding?.characterDetailSpecies?.text = species
        //adapter = DetailsEpisodeAdapter(requireContext(),episodeList)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}