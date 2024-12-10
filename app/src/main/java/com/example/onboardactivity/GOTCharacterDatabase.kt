package com.example.onboardactivity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GOTCharacterEntity::class], version = 1, exportSchema = false)
abstract class GOTCharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): GOTCharacterDao

    companion object {
        @Volatile
        private var INSTANCE: GOTCharacterDatabase? = null

        fun getInstance(context: Context): GOTCharacterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GOTCharacterDatabase::class.java,
                    "got_characters_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
