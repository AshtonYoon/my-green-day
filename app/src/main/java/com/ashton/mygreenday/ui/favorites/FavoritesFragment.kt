package com.ashton.mygreenday.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashton.mygreenday.R
import com.ashton.mygreenday.adapter.TrackAdapter
import com.ashton.mygreenday.databinding.FragmentFavoritesBinding
import com.ashton.mygreenday.viewmodel.TrackViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: TrackViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val trackAdapter = TrackAdapter {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.update(it.apply { favorite = !favorite })
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trackAdapter
        }

        viewModel.favoriteTracks.observe(viewLifecycleOwner, Observer {
            CoroutineScope(Dispatchers.IO).launch {
                trackAdapter.submitData(it)
            }
        })

//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewModel.favoriteTracks.collectLatest {
//                trackAdapter.submitData(it)
//            }
//        }
    }
}