package com.coppel.superhero.di.module

import com.coppel.superhero.core.domain.usecase.HeroInteractor
import com.coppel.superhero.core.domain.usecase.HeroUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideSearchHeroUseCase(heroInteractor: HeroInteractor): HeroUseCase
}
