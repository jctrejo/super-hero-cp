package com.coppel.superhero.core.data.local

import androidx.lifecycle.LiveData
import com.coppel.superhero.core.domain.entities.DomainEntity
import com.coppel.superhero.core.domain.entities.HeroItem

interface ILocalDataSource {
    fun getHeroes(): LiveData<DomainEntity>

    fun insertHero(hero: HeroItem)

    fun deleteHero(heroName: String)
}
