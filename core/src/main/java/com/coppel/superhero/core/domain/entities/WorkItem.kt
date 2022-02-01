package com.coppel.superhero.core.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkItem(
    val response: String,
    val occupation: String = "",
    val base: String = "",
) : Parcelable
