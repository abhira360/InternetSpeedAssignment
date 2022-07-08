package com.assignment.internetspeed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InternetSpeed::class], version = 1)
abstract class SpeedDatabase: RoomDatabase() {

    abstract fun internetSpeedDao() : InternetSpeedDAO

    companion object {
        @Volatile
        private var INSTANCE: SpeedDatabase? = null

        fun getDataBase(context: Context): SpeedDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpeedDatabase::class.java,
                    "internet_speed_database")
                    .build()

                INSTANCE = instance

                instance
            }
        }

    }

}