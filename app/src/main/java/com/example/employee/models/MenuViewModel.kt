package com.example.employee.models

import android.app.Activity
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
}