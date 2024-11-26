package com.example.onboardactivity

import GOTPerson
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
import java.util.Collections.emptyList

class GameApiService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    // Функция для получения списка персонажей GOT
    suspend fun getGOTPeople(): List<GOTPerson> {
        return withContext(Dispatchers.IO) {
            try {
                client.get("https://www.anapioficeandfire.com/api/characters").body()
            } catch (e: Exception) {
                Log.e("GameApiService", "Error fetching GOT people: ${e.message}")
                emptyList()
            }
        }
    }
}
