package com.assignment.internetspeed.utils

import android.net.TrafficStats
import java.util.*

class InternetSpeedUtils {

    companion object{
        private const val GB : Long = 1000000000
        private const val MB : Long = 1000000
        private const val KB : Long = 1000


        fun getNetworkSpeed() : String{


            var downloadSpeedOutput = ""
            var units = ""
            val mBytesPrevious = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes()

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val mBytesCurrent = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes()

            val mNetworkSpeed = mBytesCurrent - mBytesPrevious

            val mDownloadSpeedWithDecimals: Float

            if (mNetworkSpeed >= GB) {
                mDownloadSpeedWithDecimals = mNetworkSpeed.toFloat() / GB.toFloat()
                units = " gb"
            } else if (mNetworkSpeed >= MB) {
                mDownloadSpeedWithDecimals = mNetworkSpeed.toFloat() / MB.toFloat()
                units = " mb"

            } else {
                mDownloadSpeedWithDecimals = mNetworkSpeed.toFloat() / KB.toFloat()
                units = " kb"
            }


            downloadSpeedOutput = if (units != " kb" && mDownloadSpeedWithDecimals < 100) {
                String.format(Locale.US, "%.1f", mDownloadSpeedWithDecimals)
            } else {
                mDownloadSpeedWithDecimals.toInt().toString()
            }

            return (downloadSpeedOutput + units)

        }

        fun convertToBytes(value: Float, unit: String) : Long{
            if(unit == "kb"){
                return (value.toLong())* KB
            } else if(unit == "mb"){
                return (value.toLong())* MB
            } else if(unit == "gb"){
                return (value.toLong()) * GB
            }
            return 0
        }

        fun getMetricData(bytes : Long) : String{
            val dataWithDecimals : Float
            val units : String
            if (bytes >= GB) {
                dataWithDecimals = bytes.toFloat() / GB.toFloat()
                units = " gb"
            } else if (bytes >= MB) {
                dataWithDecimals = bytes.toFloat() / MB.toFloat()
                units = " mb"

            } else {
                dataWithDecimals = bytes.toFloat() / KB.toFloat()
                units = " kb"
            }


            var output=  if (units != " kb" && dataWithDecimals < 100) {
                String.format(Locale.US, "%.1f", dataWithDecimals)
            } else {
                dataWithDecimals.toInt().toString()
            }

            return output + units
        }




    }

}