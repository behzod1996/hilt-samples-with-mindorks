package com.behzoddev.hilttutorialwithmindorks.repository

import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.network.ApiService
import com.behzoddev.hilttutorialwithmindorks.utils.NetworkResult
import com.behzoddev.hilttutorialwithmindorks.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val webService: ApiService) : Repository {

    override suspend fun getUsers(): Flow<NetworkResult<List<User>>> =
        safeApiCall("Found errors in Api call"){
            webService.getUsers()
        }


}