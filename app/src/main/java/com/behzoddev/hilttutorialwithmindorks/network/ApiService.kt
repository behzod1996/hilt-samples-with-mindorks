package com.behzoddev.hilttutorialwithmindorks.network

import com.behzoddev.hilttutorialwithmindorks.database.User
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}