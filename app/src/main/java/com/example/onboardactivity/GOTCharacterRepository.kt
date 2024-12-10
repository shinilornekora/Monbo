package com.example.onboardactivity
import kotlinx.coroutines.flow.Flow

class GOTCharacterRepository(
    private val dao: GOTCharacterDao,
    private val api: GameApiService
) {

    suspend fun getCharacters(): List<GOTCharacterEntity> {
        val localData = dao.getAllCharacters()
        return if (localData.isNotEmpty()) {
            localData
        } else {
            val remoteData = api.getGOTPeople()
            val entities = remoteData.map {
                GOTCharacterEntity(
                    id = it.id,
                    name = it.name,
                    status = it.status,
                    species = it.species,
                    type = it.type,
                    gender = it.gender,
                    originName = it.origin.name,
                    locationName = it.location.name,
                    image = it.image,
                    index = it.index
                )
            }
            dao.insertCharacters(entities)
            entities
        }
    }

    suspend fun refreshCharacters() {
        val remoteData = api.getGOTPeople()
        val entities = remoteData.map {
            GOTCharacterEntity(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                originName = it.origin.name,
                locationName = it.location.name,
                image = it.image,
                index = it.index
            )
        }
        dao.insertCharacters(entities)
    }

    fun observeCharactersFlow(): Flow<List<GOTCharacterEntity>> {
        return dao.getAllCharactersFlow()
    }

    suspend fun deleteCharacter(character: GOTCharacterEntity) {
        dao.deleteCharacter(character)
    }

    suspend fun deleteAllCharacters() {
        dao.deleteAllCharacters()
    }

    suspend fun updateCharacter(character: GOTCharacterEntity) {
        dao.updateCharacter(character)
    }

}
