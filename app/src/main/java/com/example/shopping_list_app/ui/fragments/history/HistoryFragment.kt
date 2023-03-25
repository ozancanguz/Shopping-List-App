package com.example.shopping_list_app.ui.fragments.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping_list_app.R
import com.example.shopping_list_app.data.adapters.HistoryListAdapter
import com.example.shopping_list_app.databinding.FragmentHistoryBinding
import com.example.shopping_list_app.viewmodel.HistoryItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!


    private lateinit var historyItemViewModel: HistoryItemViewModel

    private lateinit var historyAdapter: HistoryListAdapter





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        historyItemViewModel = ViewModelProvider(this).get(HistoryItemViewModel::class.java)
        historyAdapter= HistoryListAdapter(historyItemViewModel)


        setupRv()
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun setupRv() {
        binding.historyrv.layoutManager= LinearLayoutManager(requireContext())
        binding.historyrv.adapter=historyAdapter
    }

    private fun observeLiveData(){
        historyItemViewModel.getAllHistoryItems.observe(viewLifecycleOwner, Observer {
            historyAdapter.setData(it)
        })
    }



}