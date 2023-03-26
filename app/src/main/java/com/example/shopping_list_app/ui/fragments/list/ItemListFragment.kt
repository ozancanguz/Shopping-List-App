package com.example.shopping_list_app.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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


    private lateinit var itemListViewModel: ItemListViewModel

    private lateinit var listadapter:ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val view = binding.root




        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        itemListViewModel = ViewModelProvider(this).get(ItemListViewModel::class.java)
        listadapter=ListAdapter(itemListViewModel)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_addFragment)
        }



        // set menu
     //   setHasOptionsMenu(true)

        // setting up rv
        setupRv()

        // observe live data and update ui
        observeLiveData()

        super.onViewCreated(view, savedInstanceState)
    }


    // create menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.itemlistfragmentmenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // menu onclick
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteAll){
            deleteAlertDialog()
        }else if(item.itemId==R.id.complete){
findNavController().navigate(R.id.action_itemListFragment_to_savedListFragment)        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val deleteAllMenuItem = menu.findItem(R.id.deleteAll)
        deleteAllMenuItem.isVisible = itemListViewModel.getAllItems.value?.isNotEmpty() == true
    }

    // delete all items alert dialog function
    private fun deleteAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Delete All Items")
        builder.setMessage("Are you sure you want to delete all items on the list?")
        builder.setPositiveButton("Yes") { _, _ ->
           itemListViewModel.deleteAll()

            Toast.makeText(requireContext(),"All items are deleted",Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }


    private fun setupRv() {
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=listadapter
    }


    private fun observeLiveData(){
        itemListViewModel.getAllItems.observe(viewLifecycleOwner, Observer {
            listadapter.setData(it)
            setHasOptionsMenu(it.isNotEmpty())

        })

        itemListViewModel.isEmpty.observe(viewLifecycleOwner) { isEmpty ->
            binding.tvEmptyList.visibility = if (isEmpty) View.VISIBLE else View.GONE

        }
    }

}