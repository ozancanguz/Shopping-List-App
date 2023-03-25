package com.example.shopping_list_app.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.data.db.history.HistoryItem
import com.example.shopping_list_app.databinding.HistoryitemrowlayoutBinding
import com.example.shopping_list_app.viewmodel.HistoryItemViewModel

class HistoryListAdapter(private val viewModel: HistoryItemViewModel) :
    RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(private val binding: HistoryitemrowlayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryItem) {
            binding.historytvTitle.text = item.currentDate
            binding.historytvName.text = item.itemName
            binding.historytvAmount.text = item.amount.toString()
        }
    }

    var historyItemList = emptyList<HistoryItem>()

    fun setData(newData: List<HistoryItem>) {
        val diffResult = DiffUtil.calculateDiff(HistoryItemDiffCallback(historyItemList, newData))
        historyItemList = newData
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryitemrowlayoutBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = historyItemList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentHistoryItem = historyItemList[position]
        holder.bind(currentHistoryItem)
    }

    // DiffUtil callback to compare old and new lists
    class HistoryItemDiffCallback(
        private val oldList: List<HistoryItem>,
        private val newList: List<HistoryItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]
    }
}
