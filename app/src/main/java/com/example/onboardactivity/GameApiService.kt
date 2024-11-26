package com.example.onboardactivity

import GOTPerson
import GOTPersonResponse
import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.random.Random

class GameApiService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    // Функция для получения случайных страниц персонажей, к сожалению, не GOT
    suspend fun getGOTPeople(): List<GOTPerson> {
        return withContext(Dispatchers.IO) {
            val pagesToFetch = List(3) { Random.nextInt(1, 41) }
            val allPeople = mutableListOf<GOTPerson>()

            try {
                for (page in pagesToFetch) {
                    val response: GOTPersonResponse = client.get("https://rickandmortyapi.com/api/character/?page=$page").body()
                    Log.d("Game", "Fetched page $page: ${response.results.size} characters.")
                    allPeople.addAll(response.results)
                }
                Log.d("Game", "Total characters fetched: ${allPeople.size}")

                allPeople.shuffle()
                val trimmedPeople = allPeople.take(50)

                val peopleWithIndex = trimmedPeople.mapIndexed { index, person ->
                    person.copy(index = index)
                }

                Log.d("Game", "Total characters fetched, trimmed and indexed: ${peopleWithIndex.size}")
                peopleWithIndex
            } catch (e: Exception) {
                Log.e("GameApiService", "Error fetching GOT people: ${e.message}")
                emptyList()
            }
        }
    }
}
