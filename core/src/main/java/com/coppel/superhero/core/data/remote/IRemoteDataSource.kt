package com.coppel.superhero.core.data.remote

import androidx.lifecycle.LiveData
import com.coppel.superhero.core.domain.entities.DomainEntity
import com.coppel.superhero.core.domain.entities.AppearanceItem
import com.coppel.superhero.core.domain.entities.BiographyItem
import com.coppel.superhero.core.domain.entities.PowerstatsItem
import com.coppel.superhero.core.domain.entities.WorkItem
import com.coppel.superhero.core.domain.entities.ConnectionsItem
import com.coppel.superhero.core.domain.entities.ImageItem
import com.coppel.superhero.core.domain.entities.ListHeroItem

interface IRemoteDataSource {
    fun getHeroes(search: String): LiveData<DomainEntity>

    fun getListHero(id: String): LiveData<ListHeroItem>

    fun getBiography(id: String): LiveData<BiographyItem>

    fun getPowerstats(id: String): LiveData<PowerstatsItem>

    fun getAppearance(id: String): LiveData<AppearanceItem>

    fun getWork(id: String): LiveData<WorkItem>

    fun getConnections(id: String): LiveData<ConnectionsItem>

    fun getImage(id: String): LiveData<ImageItem>

    fun getConnectionStatus(): LiveData<Boolean>

    fun getLoadingStatus(): LiveData<Boolean>
}
