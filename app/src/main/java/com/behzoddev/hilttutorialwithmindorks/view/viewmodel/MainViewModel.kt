package com.behzoddev.hilttutorialwithmindorks.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behzoddev.hilttutorialwithmindorks.database.User
import com.behzoddev.hilttutorialwithmindorks.repository.Repository
import com.behzoddev.hilttutorialwithmindorks.repository.RepositoryImpl
import com.behzoddev.hilttutorialwithmindorks.utils.NetworkResult
import com.behzoddev.hilttutorialwithmindorks.utils.Resource
import com.behzoddev.hilttutorialwithmindorks.utils.getViewStateFlowForNetworkCall
import com.behzoddev.hilttutorialwithmindorks.utils.safeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepositoryImpl) : ViewModel() {

    private val _users = MutableStateFlow<Resource<List<User>>>(Resource.OnLoading(isLoading = true))
    val users: StateFlow<Resource<List<User>>> get() = _users
    private val userList = ArrayList<User>()

    init {
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch {
            Log.d("Debug","viewModelScope is created")
            getViewStateFlowForNetworkCall {
                repository.getUsers()
            }.collect {
                when(it) {
                    is Resource.OnLoading -> _users.value = it
                    is Resource.OnError -> _users.value = it
                    is Resource.OnSuccess<List<User>> -> _users.value = Resource.OnSuccess(it.data)
                }
            }
        }
    }
}