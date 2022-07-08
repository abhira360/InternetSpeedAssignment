package com.assignment.internetspeed.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.assignment.internetspeed.MainActivity
import com.assignment.internetspeed.R

@RequiresApi(Build.VERSION_CODES.O)
class InternetSpeedNotification(val context: Context) {

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    val builder by lazy {
        Notification.Builder(context, context.resources.getString(R.string.channel_id))
            .setSmallIcon(R.drawable.ic_baseline_4g_mobiledata_24)
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .setContentIntent(createPendingIntent())


    }
    private fun createPendingIntent(): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        return PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun createNotification(channelId: String, channelName: String) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun updateNotification(downloadSpeed: String) {

       builder.setContentText("Internet Speed: $downloadSpeed/s")
        notificationManager.notify(context.resources.getString(R.string.notification_id).toInt(), builder.build())
    }

}