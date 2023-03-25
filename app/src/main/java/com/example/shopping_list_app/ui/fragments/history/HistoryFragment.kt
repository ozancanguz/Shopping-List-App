package com.example.shopping_list_app.ui.fragments.history

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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


        // setting up recyclerview
        setupRv()

        // observe live data and update ui
        observeLiveData()

        // set menu
        setHasOptionsMenu(true)


        super.onViewCreated(view, savedInstanceState)
    }


    private fun setupRv() {
        binding.historyrv.layoutManager= LinearLayoutManager(requireContext())
        binding.historyrv.adapter=historyAdapter
    }

    private fun observeLiveData(){
        historyItemViewModel.getAllHistoryItems.observe(viewLifecycleOwner, Observer {
            historyAdapter.setData(it)

            historyItemViewModel.isEmpty2.observe(viewLifecycleOwner) { isEmpty ->
                binding.tvEmptyList2.visibility = if (isEmpty) View.VISIBLE else View.GONE

            }


        })
    }


// create menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.historymenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // item selected on menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.historyDeleteAll){

           // delete all history items list
            deleteHistoryAlertDialog()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteHistoryAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Delete All Items")
        builder.setMessage("Are you sure you want to delete all items on the list?")
        builder.setPositiveButton("Yes") { _, _ ->
            historyItemViewModel.deleteAllHistoryItems()
            Toast.makeText(requireContext(),"All items are deleted", Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }




}