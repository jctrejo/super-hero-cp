package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BiographyItem(
    val response: String = "",
    val fullName: String = "",
    val alterEgos: String = "",
    val aliases: List<String?>?,
    val placeOfBirth: String = "",
    val firstAppearance: String = "",
    val publisher: String = "",
    val alignment: String? = "",
) : Parcelable
