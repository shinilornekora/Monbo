package com.example.onboardactivity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GOTCharacterEntity::class], version = 1, exportSchema = false)
abstract class GOTCharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): GOTCharacterDao
}
