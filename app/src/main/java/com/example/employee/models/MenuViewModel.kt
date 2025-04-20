package com.example.employee.models

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.employee.AccountActivity
import com.example.employee.FAQActivity

class MenuViewModel : ViewModel() {
    fun launchFAQScreen(activity: Activity) = activity.startActivity(FAQActivity.newIntent(activity))

    fun launchAccountScreen(activity: Activity) = activity.startActivity(AccountActivity.newIntent(activity))
}