package com.ashton.mygreenday.ui.greenday

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
import com.ashton.mygreenday.viewmodel.TrackViewModel
import com.ashton.mygreenday.databinding.FragmentGreendayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class GreendayFragment : Fragment() {
    private lateinit var binding: FragmentGreendayBinding
    private val viewModel: TrackViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_greenday, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        var trackAdapter = TrackAdapter {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.update(it.apply { favorite = !favorite })
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trackAdapter
        }

        viewModel.tracks.observe(viewLifecycleOwner, Observer {
            CoroutineScope(Dispatchers.IO).launch {
                trackAdapter.submitData(it)
            }
        })
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewModel.tracks.collectLatest {
//                trackAdapter.submitData(it)
//            }
//        }
    }
}