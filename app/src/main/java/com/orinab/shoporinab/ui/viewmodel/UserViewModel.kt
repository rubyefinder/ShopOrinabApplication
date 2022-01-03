package com.orinab.shoporinab.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orinab.shoporinab.data.model.room_db.User
import com.orinab.shoporinab.data.repository.UserRepository
import com.orinab.shoporinab.utils.abstracts.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userRepository: UserRepository,
) : BaseViewModel() {

    private val mutableLiveData = MutableLiveData<Any>()

    fun getUser(): LiveData<Any> {
        viewModelScope.launch {
            withContext(IO) { userRepository.getUser() }.let(mutableLiveData::postValue)
        }
        return mutableLiveData
    }

    fun getUsers(): LiveData<Any> {
        viewModelScope.launch {
            withContext(IO) { userRepository.getUsers() }.let(mutableLiveData::postValue)
        }
        return mutableLiveData
    }

    fun delete(): LiveData<Any> {
        viewModelScope.launch {
            withContext(IO) { userRepository.delete() }.let(mutableLiveData::postValue)
        }
        return mutableLiveData
    }

    fun insertUser(user: User): LiveData<Any> {
        viewModelScope.launch {
            withContext(IO) { userRepository.insertUser(user) }.let(mutableLiveData::postValue)
        }
        return mutableLiveData
    }

    fun updateUser(user: User): LiveData<Any> {
        viewModelScope.launch {
            withContext(IO) { userRepository.updateUser(user) }.let(mutableLiveData::postValue)
        }
        return mutableLiveData
    }

}