package com.example.shopping_list_app.ui.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopping_list_app.R
import com.example.shopping_list_app.data.adapters.ListAdapter
import com.example.shopping_list_app.databinding.FragmentItemListBinding
import com.example.shopping_list_app.viewmodel.ItemListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null

    private val binding get() = _binding!!


    private val itemListViewModel: ItemListViewModel by viewModels()

    private val listAdapter= ListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val view = binding.root


       // set menu
        setHasOptionsMenu(true)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_addFragment)
        }

        // setting up rv
        setupRv()

        // observe live data and update ui
        observeLiveData()

        return view

    }


    // create menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.itemlistfragmentmenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun setupRv() {
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=listAdapter
    }


    private fun observeLiveData(){
        itemListViewModel.getAllItems.observe(viewLifecycleOwner, Observer {
            listAdapter.setData(it)
        })
    }

}