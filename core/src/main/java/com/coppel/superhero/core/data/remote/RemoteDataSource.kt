package com.coppel.superhero.core.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coppel.superhero.core.data.api.SearchHeroApiService
import com.coppel.superhero.core.data.api.appearance.AppearanceResponse
import com.coppel.superhero.core.data.api.biography.BiographyResponse
import com.coppel.superhero.core.data.api.connections.ConnectionsResponse
import com.coppel.superhero.core.data.api.hero.ListHeroResponse
import com.coppel.superhero.core.data.api.image.ImageResponse
import com.coppel.superhero.core.data.api.powerstats.PowerstatsResponse
import com.coppel.superhero.core.data.api.work.WorkResponse
import com.coppel.superhero.core.domain.*
import com.coppel.superhero.core.domain.entities.*
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteDataSource : IRemoteDataSource {

    private val _heroes = MutableLiveData<DomainEntity>()
    private val _listHero = MutableLiveData<ListHeroItem>()
    private val _biography = MutableLiveData<BiographyItem>()
    private val _powerstats = MutableLiveData<PowerstatsItem>()
    private val _appearance = MutableLiveData<AppearanceItem>()
    private val _work = MutableLiveData<WorkItem>()
    private val _connections = MutableLiveData<ConnectionsItem>()
    private val _image = MutableLiveData<ImageItem>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _isConnected = MutableLiveData<Boolean>()

    lateinit var apiService: SearchHeroApiService

    @Inject
    constructor(apiService: SearchHeroApiService) {
        this.apiService = apiService
    }

    override fun getHeroes(search: String): LiveData<DomainEntity> {
        runQuery(search)
        val heroes: LiveData<DomainEntity> = _heroes
        return heroes
    }

    override fun getListHero(id: String): LiveData<ListHeroItem> {
        runListHeroQuery(id)
        val listHero: LiveData<ListHeroItem> = _listHero
        return listHero
    }

    override fun getBiography(id: String): LiveData<BiographyItem> {
        runBiographyQuery(id)
        val heroes: LiveData<BiographyItem> = _biography
        return heroes
    }

    override fun getPowerstats(id: String): LiveData<PowerstatsItem> {
        runPowerstatsQuery(id)
        val powerstats: LiveData<PowerstatsItem> = _powerstats
        return powerstats
    }

    override fun getAppearance(id: String): LiveData<AppearanceItem> {
        runAppearanceQuery(id)
        val appearance: LiveData<AppearanceItem> = _appearance
        return appearance
    }

    override fun getWork(id: String): LiveData<WorkItem> {
        runWorkQuery(id)
        val work: LiveData<WorkItem> = _work
        return work
    }

    override fun getConnections(id: String): LiveData<ConnectionsItem> {
        runConnectionsQuery(id)
        val connections: LiveData<ConnectionsItem> = _connections
        return connections
    }

    override fun getImage(id: String): LiveData<ImageItem> {
        runImageQuery(id)
        val image: LiveData<ImageItem> = _image
        return image
    }

    override fun getConnectionStatus(): LiveData<Boolean> {
        val isConnected: LiveData<Boolean> = _isConnected
        return isConnected
    }

    override fun getLoadingStatus(): LiveData<Boolean> {
        val isLoading: LiveData<Boolean> = _isLoading
        return isLoading
    }

    private fun runQuery(search: String) {
        apiService.getHeroes(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getHeroesListObserverRx())
    }

    private fun runListHeroQuery(id: String) {
        apiService.getListHero(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getListHeroObserverRx())
    }

    private fun runBiographyQuery(id: String) {
        apiService.getBiography(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBiographyObserverRx())
    }

    private fun runPowerstatsQuery(id: String) {
        apiService.getPowerstats(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPowerstatsObserverRx())
    }

    private fun runAppearanceQuery(id: String) {
        apiService.getAppearance(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getAppearanceObserverRx())
    }

    private fun runWorkQuery(id: String) {
        apiService.getWork(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getWorkObserverRx())
    }

    private fun runConnectionsQuery(id: String) {
        apiService.getConnections(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getConnectionsObserverRx())
    }

    private fun runImageQuery(id: String) {
        apiService.getImage(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getImageObserverRx())
    }

    private fun getListHeroObserverRx(): Observer<ListHeroResponse> {
        return object : Observer<ListHeroResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(listHeroResponse: ListHeroResponse) {
                if (listHeroResponse.response != "error") {
                    val heroItems = mutableListOf<ListHeroItem>()

                    heroItems.add(
                        ListHeroItem(
                            listHeroResponse.response ?: "",
                            listHeroResponse.id ?: "",
                            listHeroResponse.name ?: "",
                            listHeroResponse.powerstats ?: "",
                            listHeroResponse.biography?.publisher ?: "",
                            listHeroResponse.appearance?.gender ?: "",
                            listHeroResponse.work?.occupation ?: "",
                            listHeroResponse.connections?.relatives ?: "",
                            listHeroResponse.image?.url ?: ""
                        )
                    )

                    val result = ListHeroItem(
                        response = heroItems[0].response,
                        id = heroItems[0].id,
                        name = heroItems[0].name,
                        powerstats = heroItems[0].powerstats,
                        appearance = heroItems[0].appearance,
                        biography = heroItems[0].biography,
                        work = heroItems[0].work,
                        connections = heroItems[0].connections,
                        image = heroItems[0].image
                    )

                    _listHero.value = result
                } else {
                    _listHero.value = ListHeroItem(
                        response = "",
                        id = "",
                        name = "",
                        powerstats = "",
                        appearance = "",
                        biography = "",
                        work = "",
                        connections = "",
                        image = ""
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }
        }
    }

    private fun getPowerstatsObserverRx(): Observer<PowerstatsResponse> {
        return object : Observer<PowerstatsResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(powerstatsResponse: PowerstatsResponse) {
                if (powerstatsResponse.response != "error") {
                    val heroItems = mutableListOf<PowerstatsItem>()

                    heroItems.add(
                        PowerstatsItem(
                            powerstatsResponse.response ?: "",
                            powerstatsResponse.strength ?: "",
                            powerstatsResponse.durability ?: "",
                            powerstatsResponse.combat ?: "",
                            powerstatsResponse.power ?: "",
                            powerstatsResponse.speed ?: "",
                            powerstatsResponse.intelligence ?: ""
                        )
                    )

                    val result = PowerstatsItem(
                        response = heroItems[0].response,
                        strength = heroItems[0].strength,
                        durability = heroItems[0].durability,
                        combat = heroItems[0].combat,
                        power = heroItems[0].power,
                        speed = heroItems[0].speed,
                        intelligence = heroItems[0].intelligence
                    )
                    _powerstats.value = result
                } else {
                    _powerstats.value = PowerstatsItem(
                        response = "",
                        strength = "",
                        durability = "",
                        combat = "",
                        power = "",
                        speed = "",
                        intelligence = ""
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }
        }
    }

    private fun getAppearanceObserverRx(): Observer<AppearanceResponse> {
        return object : Observer<AppearanceResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(appearanceResponse: AppearanceResponse) {
                if (appearanceResponse.response != "error") {
                    val heroItems = mutableListOf<AppearanceItem>()

                    if (appearanceResponse != null) {
                        heroItems.add(
                            AppearanceItem(
                                appearanceResponse.response ?: "",
                                appearanceResponse.gender ?: "",
                                appearanceResponse.race ?: "",
                                appearanceResponse.height ?: listOf(),
                                appearanceResponse.weight ?: listOf(),
                                appearanceResponse.eyeColor ?: "",
                                appearanceResponse.hairColor ?: ""
                            )
                        )
                    }
                    val result = AppearanceItem(
                        response = heroItems[0].response,
                        gender = heroItems[0].gender,
                        race = heroItems[0].race,
                        height = heroItems[0].height,
                        weight = heroItems[0].weight,
                        eyeColor = heroItems[0].eyeColor,
                        hairColor = heroItems[0].hairColor
                    )
                    _appearance.value = result
                } else {
                    _appearance.value = AppearanceItem(
                        response = "",
                        gender = "",
                        race = "",
                        height = null,
                        weight = null,
                        eyeColor = "",
                        hairColor = ""
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }

        }
    }

    private fun getWorkObserverRx(): Observer<WorkResponse> {
        return object : Observer<WorkResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(work: WorkResponse) {
                if (work.response != "error") {
                    val heroItems = mutableListOf<WorkItem>()

                    heroItems.add(
                        WorkItem(
                            work.response ?: "",
                            work.occupation ?: "",
                            work.base ?: ""
                        )
                    )

                    val result = WorkItem(
                        response = heroItems[0].response,
                        occupation = heroItems[0].occupation,
                        base = heroItems[0].base
                    )
                    _work.value = result
                } else {
                    _work.value = WorkItem(
                        response = "",
                        occupation = "",
                        base = ""
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }

        }
    }

    private fun getConnectionsObserverRx(): Observer<ConnectionsResponse> {
        return object : Observer<ConnectionsResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(connectionsResponse: ConnectionsResponse) {
                if (connectionsResponse.response != "error") {
                    val heroItems = mutableListOf<ConnectionsItem>()

                    heroItems.add(
                        ConnectionsItem(
                            connectionsResponse?.response ?: "",
                            connectionsResponse?.groupAffiliation ?: "",
                            connectionsResponse?.relatives ?: ""
                        )
                    )
                    val result = ConnectionsItem(
                        response = heroItems[0].response,
                        groupAffiliation = heroItems[0].groupAffiliation,
                        relatives = heroItems[0].relatives
                    )
                    _connections.value = result

                } else {
                    _connections.value = ConnectionsItem(
                        response = "",
                        groupAffiliation = "",
                        relatives = ""
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }
        }
    }

    private fun getImageObserverRx(): Observer<ImageResponse> {
        return object : Observer<ImageResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(imageResponse: ImageResponse) {
                if (imageResponse.response != "error") {
                    val heroItems = mutableListOf<ImageItem>()

                    heroItems.add(
                        ImageItem(
                            imageResponse.response ?: "",
                            imageResponse.url ?: "",
                            imageResponse.name ?: "",
                            imageResponse.id ?: ""
                        )
                    )
                    val result = ImageItem(
                        response = heroItems[0].response,
                        url = heroItems[0].url,
                        name = heroItems[0].name,
                        id = heroItems[0].id
                    )
                    _image.value = result
                } else {
                    _image.value = ImageItem(
                        response = "",
                        url = "",
                        name = "",
                        id = ""
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }
        }
    }


    private fun getBiographyObserverRx(): Observer<BiographyResponse> {
        return object : Observer<BiographyResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(biographyResponse: BiographyResponse) {
                if (biographyResponse.response != "error") {
                    val heroItems = mutableListOf<BiographyItem>()

                    heroItems.add(
                        BiographyItem(
                            biographyResponse.response ?: "",
                            biographyResponse.fullName ?: "",
                            biographyResponse.alterEgos ?: "",
                            biographyResponse.aliases ?: listOf(),
                            biographyResponse.placeOfBirth ?: "",
                            biographyResponse.firstAppearance ?: "",
                            biographyResponse.publisher ?: "",
                            biographyResponse.alignment ?: ""
                        )
                    )
                    val result = BiographyItem(
                        response = heroItems[0].response,
                        fullName = heroItems[0].fullName,
                        alterEgos = heroItems[0].alterEgos,
                        aliases = heroItems[0].aliases,
                        placeOfBirth = heroItems[0].placeOfBirth,
                        firstAppearance = heroItems[0].firstAppearance,
                        publisher = heroItems[0].publisher,
                        alignment = heroItems[0].alignment
                    )
                    _biography.value = result
                } else {
                    _biography.value = BiographyItem(
                        fullName = "",
                        alterEgos = "",
                        aliases = null,
                        placeOfBirth = "",
                        firstAppearance = "",
                        publisher = "",
                        alignment = null
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }

        }
    }

    private fun getHeroesListObserverRx(): Observer<SearchHeroResponse> {
        return object : Observer<SearchHeroResponse> {
            override fun onSubscribe(d: Disposable) {
                _isConnected.value = true
                _isLoading.value = true
            }

            override fun onNext(t: SearchHeroResponse) {
                if (t.response != "error") {
                    val listResult = t.results
                    var heroItems = mutableListOf<HeroItem>()
                    if (listResult != null) {
                        for (item in listResult) {
                            heroItems.add(
                                HeroItem(
                                    item?.image?.url,
                                    item?.name,
                                    item?.biography?.aliases?.get(0),
                                    item?.powerstats?.strength,
                                    item?.powerstats?.durability,
                                    item?.powerstats?.combat,
                                    item?.powerstats?.power,
                                    item?.powerstats?.speed,
                                    item?.powerstats?.intelligence
                                )
                            )
                        }
                    }
                    val result = DomainEntity(
                        isError = false,
                        heroItems = heroItems
                    )
                    _heroes.value = result
                } else {
                    _heroes.value = DomainEntity(
                        isError = true,
                        heroItems = null
                    )
                }
            }

            override fun onError(e: Throwable) {
                _isConnected.value = false
                _isLoading.value = false
            }

            override fun onComplete() {
                _isLoading.value = false
            }
        }
    }
}
