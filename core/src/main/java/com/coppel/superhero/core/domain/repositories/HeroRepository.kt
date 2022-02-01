package com.coppel.superhero.core.domain.repositories

import androidx.lifecycle.LiveData
import com.coppel.superhero.core.data.local.LocalDataSource
import com.coppel.superhero.core.data.remote.RemoteDataSource
import com.coppel.superhero.core.domain.entities.*
import javax.inject.Inject

class HeroRepository: IHeroRepository {

    lateinit var remoteDataSource: RemoteDataSource
    lateinit var localDataSource: LocalDataSource

    @Inject
    constructor(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource){
        this.remoteDataSource = remoteDataSource
        this.localDataSource = localDataSource
    }

    override fun getHeroes(search: String): LiveData<DomainEntity> {
        return remoteDataSource.getHeroes(search)
    }

    override fun getListHero(id: String): LiveData<ListHeroItem> {
        return remoteDataSource.getListHero(id)
    }

    override fun getBiography(id: String): LiveData<BiographyItem> {
        return remoteDataSource.getBiography(id)
    }

    override fun getPowerstats(id: String): LiveData<PowerstatsItem> {
        return remoteDataSource.getPowerstats(id)
    }

    override fun getAppearance(id: String): LiveData<AppearanceItem> {
        return remoteDataSource.getAppearance(id)
    }

    override fun getWork(id: String): LiveData<WorkItem> {
        return remoteDataSource.getWork(id)
    }

    override fun getConnections(id: String): LiveData<ConnectionsItem> {
        return remoteDataSource.getConnections(id)
    }

    override fun getImage(id: String): LiveData<ImageItem> {
        return remoteDataSource.getImage(id)
    }

    override fun getConnectionStatus(): LiveData<Boolean>{
        return remoteDataSource.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean>{
        return remoteDataSource.getLoadingStatus()
    }

    override fun getFavoriteHeroes(): LiveData<DomainEntity> {
        return localDataSource.getHeroes()
    }

    override fun insertFavoriteHero(hero: HeroItem) {
        localDataSource.insertHero(hero)
    }

    /*override fun insertListFavoriteHero(hero: ListHeroItem) {
        //localDataSource.insertListHero(hero)
    }*/

    override fun deleteFavoriteHero(heroName: String) {
        localDataSource.deleteHero(heroName)
    }


}