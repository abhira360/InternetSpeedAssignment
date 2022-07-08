package com.assignment.internetspeed.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "internet_speeds")
data class InternetSpeed(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(defaultValue = "0") val speed: Long
)
