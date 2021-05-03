package com.ashton.mygreenday.ui.greenday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashton.mygreenday.R
import com.ashton.mygreenday.adapter.TrackAdapter
import com.ashton.mygreenday.viewmodel.TrackViewModel
import com.ashton.mygreenday.databinding.FragmentGreendayBinding
import kotlinx.coroutines.Dispatchers
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

        val adapter = TrackAdapter {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.update(it.apply { favorite = !favorite })
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }
}