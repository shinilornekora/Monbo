package com.example.onboardactivity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GOTCharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<GOTCharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): GOTCharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<GOTCharacterEntity>)

    @Delete
    suspend fun deleteCharacter(character: GOTCharacterEntity)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Update
    suspend fun updateCharacter(character: GOTCharacterEntity)

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAllCharactersFlow(): Flow<List<GOTCharacterEntity>>

}
