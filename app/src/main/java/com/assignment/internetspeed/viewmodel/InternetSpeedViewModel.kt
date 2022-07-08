package com.assignment.internetspeed.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.assignment.internetspeed.database.InternetSpeed
import com.assignment.internetspeed.database.SpeedDatabase
import com.assignment.internetspeed.repository.InternetSpeedRepository
import com.assignment.internetspeed.utils.InternetSpeedUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class InternetSpeedViewModel(application: Application): AndroidViewModel(application) {

    private val repository: InternetSpeedRepository

    init {
        val internetSpeedDAO = SpeedDatabase.getDataBase(application).internetSpeedDao()
        repository = InternetSpeedRepository(internetSpeedDAO)
    }

    fun insertCurrentSpeed(internetSpeed: InternetSpeed) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(internetSpeed)
    }

    val currentInternetSpeed: LiveData<Long>? = repository.currentSpeed

    val maxInternetSpeed: LiveData<Long>? = repository.maxSpeed

    val minInternetSpeed: LiveData<Long>? = repository.minSpeed

    val meanInternetSpeed: LiveData<Long>? = repository.meanSpeed

}