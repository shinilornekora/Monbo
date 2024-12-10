package com.example.onboardactivity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class GOTCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originName: String,
    val locationName: String,
    val image: String,
    val index: Int? = null
)
