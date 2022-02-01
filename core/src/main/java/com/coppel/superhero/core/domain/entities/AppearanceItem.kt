package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppearanceItem(
    val response: String,
    val gender: String = "",
    val race: String = "",
    val height: List<String?>?,
    val weight: List<String?>?,
    val eyeColor: String = "",
    val hairColor: String = ""
) : Parcelable
