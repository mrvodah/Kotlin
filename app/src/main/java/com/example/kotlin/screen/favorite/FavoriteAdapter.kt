package com.example.kotlin.screen.favorite

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.databinding.ItemFavoriteBinding
import com.example.kotlin.model.Lego
import com.example.kotlin.screen.base.recyclerview.BaseRecyclerViewAdapter
import com.example.kotlin.screen.base.recyclerview.OnItemClickListener

class FavoriteAdapter(context: Context):
    BaseRecyclerViewAdapter<Lego, RecyclerView.ViewHolder>(context){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavoriteBinding>(
            layoutInflater, R.layout.item_favorite, parent, false
        )
        return ItemViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindViewHolder(listData[position], position)
    }

    class ItemViewHolder(
        val binding: ItemFavoriteBinding,
        val onItemClickListener: OnItemClickListener<Lego>?,
        val itemFavoriteViewModel: ItemFavoriteViewModel = ItemFavoriteViewModel(onItemClickListener)
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = itemFavoriteViewModel
        }

        fun bindViewHolder(lego: Lego, position: Int){
            itemFavoriteViewModel.setData(lego, position)
            binding.ratingBar.rating = lego.rate.toFloat()
            binding.executePendingBindings()
        }

    }

}