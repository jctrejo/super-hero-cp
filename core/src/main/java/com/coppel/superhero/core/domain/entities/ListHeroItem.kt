package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListHeroItem(
    val response: String,
    val id: String,
    val name: String,
    var powerstats: String = "",
    var biography: String,
    var appearance: String,
    var work: String,
    var connections: String,
    var image: String,
) : Parcelable
