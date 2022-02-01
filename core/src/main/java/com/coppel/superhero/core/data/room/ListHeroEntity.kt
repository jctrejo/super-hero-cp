package com.coppel.superhero.core.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListHeroEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "intelligence")
    val intelligence: String,

    @ColumnInfo(name = "strength")
    val strength: String,

    @ColumnInfo(name = "speed")
    val speed: String,

    @ColumnInfo(name = "durability")
    val durability: String,

    @ColumnInfo(name = "power")
    val power: String,

    @ColumnInfo(name = "combat")
    val combat: String,

    @ColumnInfo(name = "full-name")
    val fullName: String,

    @ColumnInfo(name = "alter-egos")
    val alterEgos: String,

    @ColumnInfo(name = "aliases")
    val aliases: String,

    @ColumnInfo(name = "place-of-birth")
    val placeOfBirth: String,

    @ColumnInfo(name = "first-appearance")
    val firstAppearance: String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "alignment")
    val alignment: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "race")
    val race: String,

    @ColumnInfo(name = "height")
    val height: String,

    @ColumnInfo(name = "weight")
    val weight: String,

    @ColumnInfo(name = "eye-color")
    val eyeColor: String,

    @ColumnInfo(name = "hair-color")
    val hairColor: String,

    @ColumnInfo(name = "occupation")
    val occupation: String,

    @ColumnInfo(name = "base")
    val base: String,

    @ColumnInfo(name = "group-affiliation")
    val groupAffiliation: String,

    @ColumnInfo(name = "relatives")
    val relatives: String,
)
