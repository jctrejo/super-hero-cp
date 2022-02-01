package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageItem(
    val response: String,
    val url: String = "",
    val name: String = "",
    val id: String = ""
) : Parcelable
