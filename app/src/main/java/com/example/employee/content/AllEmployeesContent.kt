package com.example.employee.content

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.employee.db.EmployeeDAO
import com.example.employee.ui.nav.AppBar
import com.example.employee.ui.theme.EmployeeContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllEmployeesContent(
    dao: EmployeeDAO,
    drawerState: DrawerState
) {
    Scaffold(
        topBar = { AppBar(drawerState = drawerState, title = "Все сотрудники") },
        containerColor = Color.Black
    ) { innerPadding ->
        val allEmployees = remember { dao.getAllEmployees() }
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            itemsIndexed(allEmployees) { _, employee ->
                EmployeeContent(employee = employee, dao)
            }
        }
    }
}