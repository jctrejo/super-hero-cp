package com.coppel.superhero.favorites

import com.coppel.superhero.core.di.CoreComponent
import com.coppel.superhero.di.module.AppModule
import com.coppel.superhero.di.AppScope
import com.coppel.superhero.favorites.ui.FavoriteActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface FavoriteComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): FavoriteComponent
    }

    fun inject(activity: FavoriteActivity)
}
