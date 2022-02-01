package com.coppel.superhero.core.data.api.biography

import com.google.gson.annotations.SerializedName

data class BiographyResponse(

    @field:SerializedName("response")
    val response: String? = null,

    @field:SerializedName("place-of-birth")
    val placeOfBirth: String? = null,

    @field:SerializedName("aliases")
    val aliases: List<String?>? = null,

    @field:SerializedName("first-appearance")
    val firstAppearance: String? = null,

    @field:SerializedName("publisher")
    val publisher: String? = null,

    @field:SerializedName("alignment")
    val alignment: String? = null,

    @field:SerializedName("full-name")
    val fullName: String? = null,

    @field:SerializedName("alter-egos")
    val alterEgos: String? = null
)
