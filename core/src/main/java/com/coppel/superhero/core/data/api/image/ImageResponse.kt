package com.coppel.superhero.core.data.api.image

import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @field:SerializedName("response")
    val response: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null
)
