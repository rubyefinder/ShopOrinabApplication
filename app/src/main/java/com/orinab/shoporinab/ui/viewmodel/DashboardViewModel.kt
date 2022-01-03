package com.orinab.shoporinab.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orinab.shoporinab.data.repository.DashboardRepository
import com.orinab.shoporinab.utils.abstracts.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository,
) : BaseViewModel() {

    fun initDashboard(): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) { dashboardRepository.initDashboard() }.let(mutableLiveDataVersionApp::postValue)
        }
        return mutableLiveDataVersionApp
    }

    fun initCabinetKitchen(id: Int): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) { dashboardRepository.initCabinetKitchen(id) }.let(mutableLiveDataVersionApp::postValue)
        }
        return mutableLiveDataVersionApp
    }

    fun initCabinetKitchenSingle(url: String): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) { dashboardRepository.initCabinetKitchenSingle(url) }.let(mutableLiveDataVersionApp::postValue)
        }
        return mutableLiveDataVersionApp
    }


}