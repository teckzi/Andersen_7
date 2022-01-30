package com.teckzi.rickandmorty.presentation.common.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.teckzi.rickandmorty.R
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

    private lateinit var mView: View
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
        mView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
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

        mView.findViewById<Button>(R.id.buttonApplyBottomSheet).setOnClickListener {
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
        return mView
    }

    private fun characterFilter() {
        mView.findViewById<ChipGroup>(R.id.chipGroupStatus)
            .setOnCheckedChangeListener { group, selectedChipId ->
                val chip = group.findViewById<Chip>(selectedChipId)
                val text = chip.text.toString().lowercase()
                characterStatusChip = if (text != "") text else null
            }

        mView.findViewById<ChipGroup>(R.id.chipGroupGender)
            .setOnCheckedChangeListener { group, selectedChipId ->
                val chip = group.findViewById<Chip>(selectedChipId)
                val text = chip.text.toString().lowercase()
                characterGenderChip = if (text != "") text else null
            }

        mView.findViewById<EditText>(R.id.editTextSpecies).doAfterTextChanged {
            val text = it.toString()
            characterSpeciesText = if (text != "") text else null
        }

        mView.findViewById<EditText>(R.id.editTextType).doAfterTextChanged {
            val text = it.toString()
            characterTypeText = if (text != "") text else null
        }
    }

    private fun locationFilter() {
        locationFilterViews()
        mView.findViewById<TextView>(R.id.textViewSpecies).text = resources.getString(R.string.type)
        mView.findViewById<EditText>(R.id.editTextSpecies).apply {
            hint = "Type"
            doAfterTextChanged {
                locationType = it.toString()
            }
        }
        mView.findViewById<TextView>(R.id.textViewType).text =
            resources.getString(R.string.dimension)
        mView.findViewById<EditText>(R.id.editTextType).apply {
            hint = "Dimension"
            doAfterTextChanged {
                locationDimension = it.toString()
            }
        }
    }

    private fun episodeFilter() {
        episodeFilterViews()
        val seasonSpinner = mView.findViewById<Spinner>(R.id.spinnerSeason)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            seasonsList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        seasonSpinner.adapter = adapter

        seasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        val episodeSpinner = mView.findViewById<Spinner>(R.id.spinnerEpisode)
        val adapter2 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            episodesList
        )

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        episodeSpinner.adapter = adapter2
        episodeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
        mView.findViewById<TextView>(R.id.textViewStatus).gone()
        mView.findViewById<HorizontalScrollView>(R.id.scrollViewStatus).gone()
        mView.findViewById<TextView>(R.id.textViewGender).gone()
        mView.findViewById<HorizontalScrollView>(R.id.scrollViewGender).gone()
    }

    private fun episodeFilterViews() {
        locationFilterViews()
        mView.findViewById<EditText>(R.id.editTextSpecies).gone()
        mView.findViewById<TextView>(R.id.textViewSpecies).gone()
        mView.findViewById<TextView>(R.id.textViewType).gone()
        mView.findViewById<EditText>(R.id.editTextType).gone()
        mView.findViewById<TextView>(R.id.textViewEpisode).visible()
        mView.findViewById<Spinner>(R.id.spinnerSeason).visible()
        mView.findViewById<Spinner>(R.id.spinnerEpisode).visible()
    }
}