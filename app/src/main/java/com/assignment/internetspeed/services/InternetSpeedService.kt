package com.assignment.internetspeed.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.assignment.internetspeed.R
import com.assignment.internetspeed.database.InternetSpeed
import com.assignment.internetspeed.notification.InternetSpeedNotification
import com.assignment.internetspeed.utils.InternetSpeedUtils
import com.assignment.internetspeed.viewmodel.InternetSpeedViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class InternetSpeedService: Service() {

    private val binder = LocalBinder()
    private val timer by lazy { Timer() }


    private val viewModel by lazy { InternetSpeedViewModel(application) }

    inner class LocalBinder : Binder() {
        fun getService(): InternetSpeedService = this@InternetSpeedService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    private val notification by lazy { InternetSpeedNotification(this) }

    override fun onCreate() {

        super.onCreate()

        notification.createNotification(resources.getString(R.string.channel_id),resources.getString(R.string.channel_name))

        startForeground(resources.getString(R.string.notification_id).toInt(), notification.builder.build())

        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                val downloadSpeed = InternetSpeedUtils.getNetworkSpeed()
                notification.updateNotification(downloadSpeed)
                saveToDb(downloadSpeed)
            }
        }, 0, 10000)

    }

    private fun saveToDb(downloadSpeed: String) {

        val speed : String = (downloadSpeed.subSequence(0, downloadSpeed.indexOf(" ")+1)).toString()
        val units : String  = (downloadSpeed.subSequence(downloadSpeed.indexOf(" ")+1,downloadSpeed.length)).toString()

        val toBytes = InternetSpeedUtils.convertToBytes(speed.toFloat(),units)

        viewModel.insertCurrentSpeed(InternetSpeed(0 ,toBytes))



    }
}