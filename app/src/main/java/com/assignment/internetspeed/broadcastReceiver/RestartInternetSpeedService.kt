package com.assignment.internetspeed.broadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RestartInternetSpeedService: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(intent)
    }
}