package com.coppel.superhero.core.domain.usecase

import androidx.lifecycle.LiveData
import com.coppel.superhero.core.domain.entities.*
import com.coppel.superhero.core.domain.repositories.IHeroRepository
import javax.inject.Inject

class HeroInteractor: HeroUseCase {

    lateinit var heroRepository: IHeroRepository

    @Inject
    constructor(heroRepository: IHeroRepository){
        this.heroRepository = heroRepository
    }

    override fun getHeroes(search: String): LiveData<DomainEntity> {
        return heroRepository.getHeroes(search)
    }

    override fun getListHero(search: String): LiveData<ListHeroItem> {
        return heroRepository.getListHero(search)
    }

    override fun getBiography(id: String): LiveData<BiographyItem> {
        return heroRepository.getBiography(id)
    }

    override fun getPowerstats(id: String): LiveData<PowerstatsItem> {
        return heroRepository.getPowerstats(id)
    }

    override fun getAppearance(id: String): LiveData<AppearanceItem> {
        return heroRepository.getAppearance(id)
    }

    override fun getWork(id: String): LiveData<WorkItem> {
        return heroRepository.getWork(id)
    }

    override fun getConnections(id: String): LiveData<ConnectionsItem> {
        return heroRepository.getConnections(id)
    }

    override fun getImage(id: String): LiveData<ImageItem> {
        return heroRepository.getImage(id)
    }

    override fun getConnectionStatus(): LiveData<Boolean> {
        return heroRepository.getConnectionStatus()
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        return heroRepository.getLoadingStatus()
    }

    override fun getFavoriteHeroes(): LiveData<DomainEntity> {
        return heroRepository.getFavoriteHeroes()
    }

    override fun insertFavoriteHero(hero: HeroItem) {
        heroRepository.insertFavoriteHero(hero)
    }

    override fun deleteFavoriteHero(heroName: String) {
        heroRepository.deleteFavoriteHero(heroName)
    }
}
