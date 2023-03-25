package com.example.shopping_list_app.ui.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopping_list_app.R
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.databinding.FragmentAddBinding
import com.example.shopping_list_app.viewmodel.ItemListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null

    private val binding get() = _binding!!

    // init viewmodel
    private val itemListViewModel: ItemListViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root


        // set menu
        setHasOptionsMenu(true)



        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.addfragmentmenu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    // insert item into shopping list
    fun insertDb(){
        val itemName=binding.itemNameET.text.toString()
        val amount=binding.quantityET.text.toString().toInt()
        if(amount==0) {
            Toast.makeText(
                requireContext(),
                "You cannot enter 0 for item quantity.",
                Toast.LENGTH_LONG
            ).show()
        }
        val newItem= Item(0,itemName,amount)

        itemListViewModel.insertData(newItem)
        findNavController().navigate(R.id.action_addFragment_to_itemListFragment)

    }

    // save menu onclick
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_add){
            insertDb()
        }
        return super.onOptionsItemSelected(item)
    }


}