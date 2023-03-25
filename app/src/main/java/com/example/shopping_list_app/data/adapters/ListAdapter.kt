package com.example.shopping_list_app.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.data.db.item.Item
import com.example.shopping_list_app.databinding.ItemlistRowLayoutBinding

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var itemList= emptyList<Item>()

    inner class ListViewHolder(private val binding: ItemlistRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.tvName.text = item.itemName
            binding.tvAmount.text = item.amount.toString()
        }
    }

    fun setData(newData:List<Item>){
        this.itemList=newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemlistRowLayoutBinding.inflate(inflater, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}
