package com.example.shopping_list_app.ui.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_list_app.R
import com.example.shopping_list_app.databinding.FragmentItemListBinding


class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         _binding = FragmentItemListBinding.inflate(inflater, container, false)
        val view = binding.root


        return view

    }


}