package com.coppel.superhero.core.data.api

import com.coppel.superhero.core.data.api.appearance.AppearanceResponse
import com.coppel.superhero.core.data.api.biography.BiographyResponse
import com.coppel.superhero.core.data.api.connections.ConnectionsResponse
import com.coppel.superhero.core.data.api.hero.ListHeroResponse
import com.coppel.superhero.core.data.api.image.ImageResponse
import com.coppel.superhero.core.data.api.powerstats.PowerstatsResponse
import com.coppel.superhero.core.data.api.work.WorkResponse
import com.coppel.superhero.core.domain.SearchHeroResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchHeroApiService {
    @GET("search/{query}")
    fun getHeroes(
        @Path("query") query: String
    ): Observable<SearchHeroResponse>

    @GET("{id}")
    fun getListHero(
        @Path("id") id: String
    ): Observable<ListHeroResponse>

    @GET("{id}/biography")
    fun getBiography(
        @Path("id") id: String
    ): Observable<BiographyResponse>

    @GET("{id}/powerstats")
    fun getPowerstats(
        @Path("id") id: String
    ): Observable<PowerstatsResponse>

    @GET("{id}/appearance")
    fun getAppearance(
        @Path("id") id: String
    ): Observable<AppearanceResponse>

    @GET("{id}/work")
    fun getWork(
        @Path("id") id: String
    ): Observable<WorkResponse>

    @GET("{id}/connections")
    fun getConnections(
        @Path("id") id: String
    ): Observable<ConnectionsResponse>

    @GET("{id}/image")
    fun getImage(
        @Path("id") id: String
    ): Observable<ImageResponse>
}
