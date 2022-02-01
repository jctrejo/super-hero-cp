package com.coppel.superhero.core.data.api.hero

import com.coppel.superhero.core.data.api.appearance.AppearanceResponse
import com.coppel.superhero.core.data.api.biography.BiographyResponse
import com.coppel.superhero.core.data.api.connections.ConnectionsResponse
import com.coppel.superhero.core.data.api.image.ImageResponse
import com.coppel.superhero.core.data.api.work.WorkResponse
import com.google.gson.annotations.SerializedName

data class ListHeroResponse(

    @field:SerializedName("response")
    val response: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name:String? = null,

    @field:SerializedName("powerstats")
    val powerstats: String? = null,

    @field:SerializedName("biography")
    val biography: BiographyResponse? = null,

    @field:SerializedName("appearance")
    val appearance: AppearanceResponse? = null,

    @field:SerializedName("work")
    val work: WorkResponse? = null,

    @field:SerializedName("connections")
    val connections: ConnectionsResponse? = null,

    @field:SerializedName("image")
    val image: ImageResponse? = null,
)
