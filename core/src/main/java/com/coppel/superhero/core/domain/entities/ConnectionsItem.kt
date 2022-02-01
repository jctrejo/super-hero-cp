package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConnectionsItem(
    val response: String,
    val groupAffiliation: String = "",
    val relatives: String = ""
) : Parcelable
