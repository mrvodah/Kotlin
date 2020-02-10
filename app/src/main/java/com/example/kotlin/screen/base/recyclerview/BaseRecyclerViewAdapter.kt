package com.example.kotlin.screen.base.recyclerview

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.screen.utils.notNull

abstract class BaseRecyclerViewAdapter<T, V: RecyclerView.ViewHolder> constructor(
    protected val context: Context,
    protected var layoutInflater: LayoutInflater = LayoutInflater.from(
        context),
    protected var listData: MutableList<T> = mutableListOf()):
    RecyclerView.Adapter<V>() {

    protected var onItemClickListener: OnItemClickListener<T>? = null

    companion object {
        const val DEFAULT_LAST_POSITION = -1
    }

    var lastPosition = DEFAULT_LAST_POSITION

    override fun onViewDetachedFromWindow(holder: V) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun getData(): MutableList<T> {
        return listData
    }

    fun refreshData(data: MutableList<T>) {
        data.notNull {
            lastPosition = DEFAULT_LAST_POSITION
            listData.clear()
            listData.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun clearData(){
        listData.clear()
        notifyDataSetChanged()
    }

    fun addData(data: MutableList<T>) {
        data.notNull {
            listData.addAll(it)
            notifyItemRangeChanged(listData.size, it.size)
        }
    }

    fun addItem(data: T, position: Int){
        if(listData.size == 0){
            listData.add(data)
            notifyDataSetChanged()
        }
        else{
            listData.add(data)
            notifyItemInserted(position)
        }
    }

    fun registerOnItemClickListener(
        onItemClickListener: OnItemClickListener<T>
    ) {
        this.onItemClickListener = onItemClickListener
    }
}