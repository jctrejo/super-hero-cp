package com.coppel.superhero.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coppel.superhero.core.domain.usecase.HeroUseCase
import com.coppel.superhero.di.AppScope
import javax.inject.Inject

@AppScope
class ViewModelFactory : ViewModelProvider.NewInstanceFactory {

    lateinit var heroUseCase: HeroUseCase

    @Inject
    constructor(heroUseCase: HeroUseCase) {
        this.heroUseCase = heroUseCase
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(heroUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
