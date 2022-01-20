package com.teckzi.rickandmorty.presentation.screens.episode_detail_screen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.teckzi.rickandmorty.util.getEpisodeString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment(R.layout.fragment_episode_detail) {

    private val viewModel by viewModels<EpisodeDetailViewModel>()
    private val binding by viewBinding(FragmentEpisodeDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedEpisode.collectLatest { episode ->
                episode?.let { it ->
                    loadEpisodeData(
                        name = it.name,
                        episode = it.episode,
                        airDate = it.airDate
                    )
                }
            }
        }
    }

    private fun loadEpisodeData(
        name: String,
        episode: String,
        airDate: String,
    ) {
        with(binding) {
            episodeDetailsName.text = name
            episodeDetailsEpisode.text = episode.getEpisodeString()
            episodeDetailsAirDate.text = airDate
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