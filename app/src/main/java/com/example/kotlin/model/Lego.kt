package com.example.kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lego(
    val id: String,
    val cate_id: String,
    val name: String,
    val author: String,
    val quantity: String,
    val price: String,
    val image: String,
    val detail: String,
    val rate: String,
    val remain: Int,
    val sale: String? = null,
    val sale_id: String? = null
): Parcelable