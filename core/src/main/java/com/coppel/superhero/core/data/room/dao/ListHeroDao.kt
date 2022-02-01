package com.coppel.superhero.core.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coppel.superhero.core.data.room.ListHeroEntity
import io.reactivex.Observable


@Dao
interface ListHeroDao {

    @Query("SELECT * FROM listHeroEntity")
    fun getAllListHeroes(): Observable<List<ListHeroEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(listHeroEntity: ListHeroEntity)

    @Query("DELETE FROM listHeroEntity WHERE name = :name")
    fun delete(name: String)
}