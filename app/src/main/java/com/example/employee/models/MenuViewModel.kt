package com.example.employee.models

import android.app.Activity
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.employee.AccountActivity
import com.example.employee.FAQActivity
import com.example.employee.NotificationActivity
import com.example.employee.PrivacyActivity

class MenuViewModel : ViewModel() {
    fun launchFAQScreen(activity: Activity) = activity.startActivity(FAQActivity.newIntent(activity))

    fun launchAccountScreen(activity: Activity) = activity.startActivity(AccountActivity.newIntent(activity))

    fun launchNotificationScreen(activity: Activity) = activity.startActivity(NotificationActivity.newIntent(activity))

    fun launchPrivacyScreen(activity: Activity) = activity.startActivity(PrivacyActivity.newIntent(activity))

    fun openLink(url: String, activity: Activity) {
        val intent = Intent(Intent.ACTION_VIEW).apply { data = url.toUri() }
        activity.startActivity(intent)
    }
}