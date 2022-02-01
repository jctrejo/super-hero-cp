package com.coppel.superhero.core.domain.repositories

import androidx.lifecycle.LiveData
import com.coppel.superhero.core.domain.entities.*

interface IHeroRepository {

    fun getHeroes(search: String): LiveData<DomainEntity>

    fun getListHero(search: String): LiveData<ListHeroItem>

    fun getBiography(id: String): LiveData<BiographyItem>

    fun getPowerstats(id: String): LiveData<PowerstatsItem>

    fun getAppearance(id: String): LiveData<AppearanceItem>

    fun getWork(id: String): LiveData<WorkItem>

    fun getConnections(id: String): LiveData<ConnectionsItem>

    fun getImage(id: String): LiveData<ImageItem>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>

    fun getFavoriteHeroes(): LiveData<DomainEntity>

    fun insertFavoriteHero(hero: HeroItem)

    //fun insertListFavoriteHero(hero: ListHeroItem)

    fun deleteFavoriteHero(heroName: String)
}
