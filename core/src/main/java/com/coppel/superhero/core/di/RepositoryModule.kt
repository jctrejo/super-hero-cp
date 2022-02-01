package com.coppel.superhero.core.di

import com.coppel.superhero.core.data.api.NetworkModule
import com.coppel.superhero.core.domain.repositories.IHeroRepository
import com.coppel.superhero.core.domain.repositories.HeroRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: HeroRepository): IHeroRepository
}
