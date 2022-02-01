package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PowerstatsItem(
    val response: String,
    val intelligence: String = "",
    val strength: String = "",
    val speed: String = "",
    val durability: String = "",
    val power: String = "",
    val combat: String = "",
) : Parcelable
