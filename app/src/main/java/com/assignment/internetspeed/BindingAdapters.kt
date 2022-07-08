package com.assignment.internetspeed

import android.widget.TextView

import androidx.databinding.BindingAdapter
import com.assignment.internetspeed.utils.InternetSpeedUtils
import java.net.IDN


@BindingAdapter("android:text")
fun setText(view: TextView, text: String?) {

    if (text != null) {
        view.text =  InternetSpeedUtils.getMetricData(text.toLong()) + "/s"
    }
}