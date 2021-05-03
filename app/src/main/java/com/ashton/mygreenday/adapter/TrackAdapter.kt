package com.ashton.mygreenday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashton.mygreenday.R
import com.ashton.mygreenday.databinding.ItemTrackBinding
import com.ashton.mygreenday.model.Track

class TrackAdapter(val favoriteClick: (Track) -> Unit) :
    ListAdapter<Track, TrackAdapter.ViewHolder>(
        MovieDiffUtil
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemTrackBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_track
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class ViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()

            binding.favorite.setOnClickListener {
                favoriteClick(track)
            }
        }
    }

    companion object MovieDiffUtil : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean {
            return oldItem == newItem
        }
    }


}