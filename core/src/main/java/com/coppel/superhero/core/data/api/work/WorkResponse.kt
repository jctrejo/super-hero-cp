package com.coppel.superhero.core.data.api.work

import com.google.gson.annotations.SerializedName

data class WorkResponse(

    @field:SerializedName("response")
    val response: String? = null,

    @field:SerializedName("occupation")
    val occupation: String? = null,

    @field:SerializedName("base")
    val base: String? = null
)
