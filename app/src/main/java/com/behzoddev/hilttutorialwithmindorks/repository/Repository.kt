package com.behzoddev.hilttutorialwithmindorks.repository

import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.utils.NetworkState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getUsers() : Flow<NetworkState<List<User>>>
}