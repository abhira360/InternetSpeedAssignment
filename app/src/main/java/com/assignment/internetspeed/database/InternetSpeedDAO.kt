package com.assignment.internetspeed.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InternetSpeedDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveCurrentSpeed(internetSpeed: InternetSpeed)

    @Query("Select speed from internet_speeds order by id desc limit 1")
    fun getCurrentSpeed(): LiveData<Long>?

    @Query("Select avg(speed) from internet_speeds")
    fun getMeanSpeed(): LiveData<Long>?

    @Query("Select max(speed) from internet_speeds")
    fun getMaxSpeed(): LiveData<Long>?

    @Query("Select min(speed) from internet_speeds")
    fun getMinSpeed(): LiveData<Long>?

}