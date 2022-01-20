package com.teckzi.rickandmorty.presentation.screens.location_detail_screen

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
import com.teckzi.rickandmorty.databinding.FragmentLocationDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationDetailFragment : Fragment(R.layout.fragment_location_detail) {

    private val viewModel by viewModels<LocationDetailViewModel>()
    private val binding by viewBinding(FragmentLocationDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.selectedLocation.collectLatest { location ->
                location?.let { it ->
                    loadLocationData(
                        name = it.name,
                        type = it.type,
                        dimension = it.dimension
                    )
                }
            }
        }
    }

    private fun loadLocationData(
        name: String,
        type: String,
        dimension: String,
    ) {
        with(binding) {
            locationDetailsName.text = name
            locationDetailsType.text = type
            locationDetailsDimension.text = dimension
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