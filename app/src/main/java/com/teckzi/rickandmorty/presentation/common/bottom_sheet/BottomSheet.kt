package com.teckzi.rickandmorty.presentation.common.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.FragmentBottomSheetBinding
import com.teckzi.rickandmorty.util.Constants.CHARACTER_TYPE
import com.teckzi.rickandmorty.util.Constants.EPISODE_TYPE
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_CHARACTER
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_EPISODE
import com.teckzi.rickandmorty.util.Constants.FILTER_RETURN_BACK_TO_LOCATION
import com.teckzi.rickandmorty.util.Constants.FILTER_TYPE_ARGUMENT_KEY
import com.teckzi.rickandmorty.util.Constants.LOCATION_TYPE
import com.teckzi.rickandmorty.util.gone
import com.teckzi.rickandmorty.util.setStringToSeason
import com.teckzi.rickandmorty.util.visible

class BottomSheet : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentBottomSheetBinding::bind)
    private var sheetKey = "character"
    private var characterStatusChip: String? = ""
    private var characterGenderChip: String? = ""
    private var characterSpeciesText: String? = ""
    private var characterTypeText: String? = ""
    private var locationType: String? = ""
    private var locationDimension: String? = ""
    private var season: String? = ""
    private var episode: String? = ""

    private val seasonsList =
        listOf("All seasons", "Season 1", "Season 2", "Season 3", "Season 4", "Season 5")
    private val episodesList = listOf(
        "All episodes",
        "Episode 1",
        "Episode 2",
        "Episode 3",
        "Episode 4",
        "Episode 5",
        "Episode 6",
        "Episode 7",
        "Episode 8",
        "Episode 9",
        "Episode 10",
        "Episode 11"
    )
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = arguments?.getString(FILTER_TYPE_ARGUMENT_KEY)
        sheetKey = args.toString()
        when (sheetKey) {
            CHARACTER_TYPE -> {
                characterFilter()
            }
            LOCATION_TYPE -> {
                locationFilter()
            }
            EPISODE_TYPE -> {
                episodeFilter()
            }
        }

        binding.buttonApplyBottomSheet.setOnClickListener {
            when (sheetKey) {
                CHARACTER_TYPE -> {
                    findNavController().navigate(
                        R.id.action_bottomSheet_to_characterFragment,
                        bundleOf(FILTER_RETURN_BACK_TO_CHARACTER to "$characterStatusChip,$characterSpeciesText,$characterTypeText,$characterGenderChip")
                    )
                }
                LOCATION_TYPE -> {
                    findNavController().navigate(
                        R.id.action_bottomSheet_to_locationFragment,
                        bundleOf(FILTER_RETURN_BACK_TO_LOCATION to "$locationType,$locationDimension")
                    )
                }
                EPISODE_TYPE -> {
                    findNavController().navigate(
                        R.id.action_bottomSheet_to_episodeFragment,
                        bundleOf(FILTER_RETURN_BACK_TO_EPISODE to "$season$episode")
                    )
                }
            }
        }
        return binding.root
    }

    private fun characterFilter() {
        binding.chipGroupStatus.setOnCheckedChangeListener { group, selectedChipId ->
                val chip = group.findViewById<Chip>(selectedChipId)
                val text = chip.text.toString().lowercase()
                characterStatusChip = if (text != "") text else null
            }

        binding.chipGroupGender.setOnCheckedChangeListener { group, selectedChipId ->
                val chip = group.findViewById<Chip>(selectedChipId)
                val text = chip.text.toString().lowercase()
                characterGenderChip = if (text != "") text else null
            }

        binding.editTextSpecies.doAfterTextChanged {
            val text = it.toString()
            characterSpeciesText = if (text != "") text else null
        }

        binding.editTextType.doAfterTextChanged {
            val text = it.toString()
            characterTypeText = if (text != "") text else null
        }
    }

    private fun locationFilter() {
        locationFilterViews()
        binding.textViewSpecies.text = resources.getString(R.string.type)
        binding.editTextSpecies.apply {
            hint = "Type"
            doAfterTextChanged {
                locationType = it.toString()
            }
        }
       binding.textViewType.text =
            resources.getString(R.string.dimension)
        binding.editTextType.apply {
            hint = "Dimension"
            doAfterTextChanged {
                locationDimension = it.toString()
            }
        }
    }

    private fun episodeFilter() {
        episodeFilterViews()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            seasonsList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSeason.adapter = adapter

        binding.spinnerSeason.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                season = when (parent?.getItemAtPosition(position)) {
                    "All seasons" -> ""
                    else -> "${parent?.getItemAtPosition(position)}".setStringToSeason()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        val adapter2 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            episodesList
        )

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEpisode.adapter = adapter2
        binding.spinnerEpisode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                episode = when (parent?.getItemAtPosition(position)) {
                    "All episodes" -> ""
                    else -> "${parent?.getItemAtPosition(position)}".setStringToSeason()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

    }

    private fun locationFilterViews() {
        binding.textViewStatus.gone()
        binding.scrollViewStatus.gone()
        binding.textViewGender.gone()
        binding.scrollViewGender.gone()
    }

    private fun episodeFilterViews() {
        locationFilterViews()
        binding.editTextSpecies.gone()
        binding.textViewSpecies.gone()
        binding.textViewType.gone()
        binding.editTextType.gone()
        binding.textViewEpisode.visible()
        binding.spinnerSeason.visible()
        binding.spinnerEpisode.visible()
    }
}