package com.example.employee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.employee.db.AppDatabase
import com.example.employee.db.EmployeeDAO
import com.example.employee.screens.PrivacyScreen

class PrivacyActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var dao: EmployeeDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        db = AppDatabase.getDatabase(this)
        dao = db.getEmployeeDao()

        window.navigationBarColor = resources.getColor(R.color.purple_500)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        super.onCreate(savedInstanceState)
        setContent {
            PrivacyScreen(this, dao = dao)
        }
    }

    companion object {
        fun newIntent(activity: Activity): Intent = Intent(activity, PrivacyActivity::class.java)
    }
}