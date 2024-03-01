package ru.pakarpichev.randomuserapp.data.remote

import retrofit2.http.GET

interface RandomUserApi {

    @GET("?results=20")
    suspend fun getUser(): RandomUserDto?

    companion object {
        const val BASE_URL = "https://randomuser.me/api/"
    }
}