package com.coppel.superhero.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.coppel.superhero.core.domain.entities.*
import com.coppel.superhero.core.domain.usecase.HeroUseCase

class MainViewModel(private val heroUseCase: HeroUseCase): ViewModel() {

    fun getHeroes(search: String): LiveData<DomainEntity>{
        return heroUseCase.getHeroes(search)
    }

    fun getListHero(id: String): LiveData<ListHeroItem>{
        return heroUseCase.getListHero(id)
    }

    fun getBiographyHeroes(id: String): LiveData<BiographyItem>{
        return heroUseCase.getBiography(id)
    }

    fun getPowerstats(id: String): LiveData<PowerstatsItem>{
        return heroUseCase.getPowerstats(id)
    }

    fun getAppearance(id: String): LiveData<AppearanceItem>{
        return heroUseCase.getAppearance(id)
    }

    fun getWork(id: String): LiveData<WorkItem>{
        return heroUseCase.getWork(id)
    }

    fun getConnections(id: String): LiveData<ConnectionsItem>{
        return heroUseCase.getConnections(id)
    }

    fun getImage(id: String): LiveData<ImageItem>{
        return heroUseCase.getImage(id)
    }

    fun getConnectionStatus(): LiveData<Boolean>{
        return heroUseCase.getConnectionStatus()
    }

    fun getLoadingStatus(): LiveData<Boolean>{
        return heroUseCase.getLoadingStatus()
    }

    fun getFavoriteHeroes(): LiveData<DomainEntity>{
        return heroUseCase.getFavoriteHeroes()
    }

    fun insertFavoriteHero(hero: HeroItem){
        heroUseCase.insertFavoriteHero(hero)
    }

/*    fun insertListFavoriteHero(hero: ListHeroItem){
        heroUseCase.insertListFavoriteHero(hero)
    }*/

    fun deleteFavoriteHero(heroName: String){
        heroUseCase.deleteFavoriteHero(heroName)
    }
}
