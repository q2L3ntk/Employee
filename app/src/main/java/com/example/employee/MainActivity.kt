package com.example.employee

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.employee.components.Notification
import com.example.employee.content.MainNavigation
import com.example.employee.db.AppDatabase
import com.example.employee.db.EmployeeDAO
import com.example.employee.models.MenuViewModel
import com.example.employee.ui.theme.EmployeeTheme

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var dao: EmployeeDAO
    private val viewModel: MenuViewModel = MenuViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notification = Notification(this)
        db = AppDatabase.getDatabase(this)
        dao = db.getEmployeeDao()

        window.navigationBarColor = resources.getColor(R.color.purple_500)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        setContent {
            EmployeeTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(dao = dao, activity = this, viewModel = viewModel, notification = notification)
                }
            }
        }
    }
}