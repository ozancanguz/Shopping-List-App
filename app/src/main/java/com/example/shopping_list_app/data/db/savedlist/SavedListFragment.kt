package com.example.shopping_list_app.data.db.savedlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shopping_list_app.R
import com.example.shopping_list_app.databinding.FragmentSavedListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedListFragment : Fragment() {
    private lateinit var viewModel: SavedListViewModel
    private lateinit var savedListAdapter: SavedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSavedListBinding.inflate(inflater, container, false)

        savedListAdapter = SavedListAdapter(onSavedListClickListener = this::onSavedListClicked)
        binding.savedListRecyclerView.adapter = savedListAdapter

        viewModel = ViewModelProvider(this).get(SavedListViewModel::class.java)
        observeSavedLists()

        return binding.root
    }

    private fun observeSavedLists() {
        viewModel.allSavedLists.observe(viewLifecycleOwner) { savedLists ->
            savedListAdapter.submitList(savedLists)
        }
    }

    private fun onSavedListClicked(savedList: SavedList) {
        // Handle saved list item click
    }
}