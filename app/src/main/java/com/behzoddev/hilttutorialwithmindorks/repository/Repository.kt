package com.behzoddev.hilttutorialwithmindorks.repository

import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getUsers() : Flow<NetworkResult<List<User>>>
}