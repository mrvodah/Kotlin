package com.example.kotlin.screen.home

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.databinding.ItemLegoBinding
import com.example.kotlin.model.Lego
import com.example.kotlin.screen.base.recyclerview.BaseRecyclerViewAdapter
import com.example.kotlin.screen.base.recyclerview.OnItemClickListener

class CarsAdapter(context: Context):
    BaseRecyclerViewAdapter<Lego, RecyclerView.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemLegoBinding>(
            layoutInflater, R.layout.item_lego, parent, false
        )
        return ItemViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindViewHolder(listData[position], position)
    }

    class ItemViewHolder(
        val binding: ItemLegoBinding,
        private val onItemClickListener: OnItemClickListener<Lego>?,
        val itemLegoHomeViewModel: ItemLegoHomeViewModel = ItemLegoHomeViewModel(onItemClickListener)):
                RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = itemLegoHomeViewModel
        }

        fun bindViewHolder(lego: Lego, position: Int){
            itemLegoHomeViewModel.setData(lego, position)
            binding.executePendingBindings()
        }

    }

}