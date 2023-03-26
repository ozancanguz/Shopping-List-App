package com.example.shopping_list_app.data.db.savedlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.R
import com.example.shopping_list_app.databinding.SavedListItemBinding
import java.text.SimpleDateFormat
import java.util.*

class SavedListAdapter(private val onSavedListClickListener: (SavedList) -> Unit) :
    ListAdapter<SavedList, SavedListAdapter.ViewHolder>(SavedListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SavedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val savedList = getItem(position)
        holder.bind(savedList)
        holder.itemView.setOnClickListener { onSavedListClickListener(savedList) }
    }

    inner class ViewHolder(private val binding: SavedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(savedList: SavedList) {
            binding.timeTextView.text =
                SimpleDateFormat("MMM dd, yyyy - HH:mm:ss").format(Date(savedList.time))
            binding.itemCountTextView.text =
                "${savedList.items.size} ${binding.root.context.getString(R.string.items)}"
        }
    }
}

class SavedListDiffCallback : DiffUtil.ItemCallback<SavedList>() {
    override fun areItemsTheSame(oldItem: SavedList, newItem: SavedList) =
        oldItem.time == newItem.time

    override fun areContentsTheSame(oldItem: SavedList, newItem: SavedList) =
        oldItem == newItem
}
