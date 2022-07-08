package com.assignment.internetspeed.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.assignment.internetspeed.database.InternetSpeed
import com.assignment.internetspeed.database.InternetSpeedDAO

class InternetSpeedRepository(private val internetSpeedDAO: InternetSpeedDAO) {

    val currentSpeed : LiveData<Long>? = internetSpeedDAO.getCurrentSpeed()

    val maxSpeed: LiveData<Long>? = internetSpeedDAO.getMaxSpeed()

    val minSpeed: LiveData<Long>? = internetSpeedDAO.getMinSpeed()

    val meanSpeed: LiveData<Long>? = internetSpeedDAO.getMeanSpeed()

    @WorkerThread
    fun insert(internetSpeed: InternetSpeed) {
        internetSpeedDAO.saveCurrentSpeed(internetSpeed)
    }
}