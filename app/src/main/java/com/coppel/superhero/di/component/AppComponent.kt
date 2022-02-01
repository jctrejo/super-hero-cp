package com.coppel.superhero.di.component

import com.coppel.superhero.core.di.CoreComponent
import com.coppel.superhero.di.module.AppModule
import com.coppel.superhero.di.AppScope
import com.coppel.superhero.ui.detail.DetailActivity
import com.coppel.superhero.ui.detail.DetailHeroActivity
import com.coppel.superhero.ui.main.MainActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)

    fun inject(activity: DetailActivity)

    fun inject(activity: DetailHeroActivity)
}
