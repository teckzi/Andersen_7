package com.teckzi.rickandmorty.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.teckzi.rickandmorty.R
import com.teckzi.rickandmorty.databinding.ItemCharacterBinding
import com.teckzi.rickandmorty.domain.model.CharacterModel
import com.teckzi.rickandmorty.presentation.screens.character_screen.CharacterFragmentDirections

private const val TAG = "TAG CharacterAdapter"
class CharacterAdapter(context: Context) :
    PagingDataAdapter<CharacterModel, CharacterViewHolder>(DiffUtil()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val viewBinding by viewBinding(ItemCharacterBinding::bind)

    fun bind(item: CharacterModel?) {
        with(viewBinding) {
            itemCharacterImageView.load(item?.image) {
                placeholder(R.drawable.image_placeholder)
                transformations(CircleCropTransformation())
            }
            itemCharacterNameTextView.text = item?.name
            itemCharacterInfo.text = "${item?.species}, ${item?.gender}"
            itemCharacterStatus.text = item?.status
            when (item?.status) {
                "Alive" -> itemCharacterStatus.setTextColor(Color.parseColor("#2EED0C"))
                "Dead" -> itemCharacterStatus.setTextColor(Color.parseColor("#FF0000"))
                "unknown" -> itemCharacterStatus.setTextColor(Color.parseColor("#C4FFFFFF"))
            }
            root.setOnClickListener {
                val id = item!!.id
                Log.d(TAG, "id $id")
                val action =
                    CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailScreen(id)
                Navigation.findNavController(it).navigate(action)
            }
        }

    }
}