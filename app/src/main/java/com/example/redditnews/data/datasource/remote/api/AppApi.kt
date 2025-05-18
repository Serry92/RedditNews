package com.example.redditnews.data.datasource.remote.api

import com.example.redditnews.data.datasource.remote.dto.RedditResponse
import retrofit2.http.GET

interface AppApi {
    @GET("r/kotlin/.json")
    suspend fun getKotlinNews(): RedditResponse
}