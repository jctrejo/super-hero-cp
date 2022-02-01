package com.coppel.superhero.core.di

import android.content.Context
import com.coppel.superhero.core.domain.repositories.IHeroRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): IHeroRepository
}
