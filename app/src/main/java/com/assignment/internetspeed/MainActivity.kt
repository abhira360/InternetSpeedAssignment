package com.assignment.internetspeed

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.assignment.internetspeed.broadcastReceiver.RestartInternetSpeedService
import com.assignment.internetspeed.databinding.ActivityMainBinding
import com.assignment.internetspeed.notification.InternetSpeedNotification
import com.assignment.internetspeed.services.InternetSpeedService
import com.assignment.internetspeed.viewmodel.InternetSpeedViewModel

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var mService: InternetSpeedService
    private var mBound: Boolean = false

    private val viewModel by lazy { ViewModelProvider(this)[InternetSpeedViewModel::class.java] }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
            val binder = service as InternetSpeedService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }

    }

    private val notification by lazy { InternetSpeedNotification(this) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        notification.createNotification(getString(R.string.channel_id),getString(R.string.channel_name))
        binding.viewModel = viewModel
        binding.lifecycleOwner = this



    }


    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, InternetSpeedService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {

        val broadcastIntent = Intent()
        broadcastIntent.action = "Restart"
        broadcastIntent.setClass(this, RestartInternetSpeedService::class.java)
        this.sendBroadcast(broadcastIntent)
        super.onDestroy()

    }
}