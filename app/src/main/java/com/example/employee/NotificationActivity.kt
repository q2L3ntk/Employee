package com.example.employee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.employee.screens.NotificationScreen

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.navigationBarColor = resources.getColor(R.color.purple_500)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen(this)
        }
    }

    companion object {
        fun newIntent(activity: Activity): Intent = Intent(activity, NotificationActivity::class.java)
    }
}