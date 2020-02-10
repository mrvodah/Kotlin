package com.example.kotlin.screen.base.recyclerview

interface OnItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}