package com.coppel.superhero.core.data.api.connections

import com.google.gson.annotations.SerializedName

data class ConnectionsResponse(

    @field:SerializedName("response")
    val response: String? = null,

    @field:SerializedName("relatives")
    val relatives: String? = null,

    @field:SerializedName("group-affiliation")
    val groupAffiliation: String? = null
)
