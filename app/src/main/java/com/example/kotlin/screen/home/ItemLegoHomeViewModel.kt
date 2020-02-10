package com.example.kotlin.screen.home

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.kotlin.model.Lego
import com.example.kotlin.screen.base.recyclerview.OnItemClickListener
import com.example.kotlin.screen.utils.notNull

class ItemLegoHomeViewModel(
    val onItemClickListener: OnItemClickListener<Lego>? = null,
    var position: Int = 0,
    @Bindable var lego: Lego? = null
) : BaseObservable() {

    fun setData(lego: Lego?, position: Int) {
        lego.notNull {
            this.lego = it
            this.position = position
            notifyPropertyChanged(BR.lego)
        }
    }

    fun onItemClick() {
        onItemClickListener.notNull {
            it.onItemClick(lego!!, position)
        }
    }

}