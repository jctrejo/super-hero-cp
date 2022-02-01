package com.coppel.superhero

import android.app.Application
import com.coppel.superhero.core.di.CoreComponent
import com.coppel.superhero.core.di.DaggerCoreComponent
import com.coppel.superhero.di.component.AppComponent
import com.coppel.superhero.di.component.DaggerAppComponent

open class MyApplication : Application() {

    val coreComponent: CoreComponent by lazy{
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}
