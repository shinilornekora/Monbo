package com.example.onboardactivity


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
                    image = it.image
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
                image = it.image
            )
        }
        dao.insertCharacters(entities)
    }
}
