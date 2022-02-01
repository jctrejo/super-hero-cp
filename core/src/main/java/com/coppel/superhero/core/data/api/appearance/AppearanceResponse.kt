package com.coppel.superhero.core.data.api.appearance

import com.google.gson.annotations.SerializedName

data class AppearanceResponse(
    @field:SerializedName("response")
    val response: String? = null,

    @field:SerializedName("eye-color")
    val eyeColor: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("race")
    val race: String? = null,

    @field:SerializedName("weight")
    val weight: List<String?>? = null,

    @field:SerializedName("height")
    val height: List<String?>? = null,

    @field:SerializedName("hair-color")
    val hairColor: String? = null
)