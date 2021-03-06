package com.ashton.mygreenday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashton.mygreenday.R
import com.ashton.mygreenday.databinding.ItemTrackBinding
import com.ashton.mygreenday.model.Track

class TrackAdapter(val favoriteClick: (Track) -> Unit) :
    PagingDataAdapter<Track, TrackAdapter.ViewHolder>(
        TrackDiffUtil
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
        val track = getItem(position)
        if(track != null) holder.bind(track)
    }

    inner class ViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
            binding.executePendingBindings()

            binding.favorite.setOnClickListener {
                favoriteClick(track)
                refresh()
            }
        }
    }

    // TODO: DiffUtil이 false, true를 번갈아가며 리턴한다 -_-;
    // (n % 2 == 1)인 n번째 업데이트에서는 oldItem, newItem이 같은 객체를 참조
    // (n % 2 == 0)인 n번째 업데이트에서는 oldItem과 newItem이 서로 다른 객체를 참조함
    companion object TrackDiffUtil : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean {
            return false
        }
    }
}