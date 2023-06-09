package com.example.shopping_list_app.ui.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shopping_list_app.R
import com.example.shopping_list_app.data.db.history.HistoryItem
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.databinding.FragmentAddBinding
import com.example.shopping_list_app.viewmodel.HistoryItemViewModel
import com.example.shopping_list_app.viewmodel.ItemListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null

    private val binding get() = _binding!!

    // init viewmodel
    private val itemListViewModel: ItemListViewModel by viewModels()


    private val historyItemViewModel: HistoryItemViewModel by viewModels()


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
    fun insertDb(): Boolean {
        val itemName = binding.itemNameET.text?.toString()?.trim()
        val amountString = binding.quantityET.text?.toString()?.trim()

        if (itemName.isNullOrEmpty() || amountString.isNullOrEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please enter a valid item name and quantity.",
                Toast.LENGTH_LONG
            ).show()
            return false // exit the function and return false to indicate failure
        }

        val amount = amountString.toIntOrNull()

        if (amount == null || amount <= 0) {
            Toast.makeText(
                requireContext(),
                "Please enter a valid quantity greater than 0.",
                Toast.LENGTH_LONG
            ).show()
            return false // exit the function and return false to indicate failure
        }

        val newItem = Item(0, itemName, amount)

        itemListViewModel.insertOrUpdate(newItem) // call insertOrUpdate instead of insertData

        findNavController().navigate(R.id.action_addFragment_to_itemListFragment)
        return true // return true to indicate success
    }


    fun insertHistoryDb(){
        val itemName=binding.itemNameET.text.toString()
        val amount=binding.quantityET.text.toString().toInt()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val dateTimeString = dateFormat.format(calendar.time)
        val newHistoryItem= HistoryItem(0,itemName,amount,dateTimeString)
        historyItemViewModel.insertHistoryItem(newHistoryItem)

    }


    // save menu onclick
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_add){
            val isInsertionSuccessful = insertDb()
            if (isInsertionSuccessful) {
                insertHistoryDb()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}