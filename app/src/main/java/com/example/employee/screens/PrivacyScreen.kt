package com.example.employee.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employee.PrivacyActivity
import com.example.employee.content.WarningContent
import com.example.employee.db.EmployeeDAO
import com.example.employee.ui.theme.DarkViolet

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyScreen(activity: Activity, dao: EmployeeDAO) {
    var warning by remember { mutableStateOf(false) }
    if (warning) {
        WarningContent(onClose = { warning = false }, dao = dao)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Настройки конфиденциальности",
                        maxLines = 1,
                        color = colorScheme.onPrimary,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkViolet)
            )
        },
        containerColor = Color.Black
    ) {
        LazyColumn(modifier = Modifier.padding(top = 60.dp)) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Удалить всех сотрудников",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    Button(
                        onClick = { warning = true },
                        modifier = Modifier
                            .width(100.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkViolet),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(text = "Удалить", color = Color.White, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PrivacyScreenPreview() {
//    val activity: Activity = PrivacyActivity()
//    PrivacyScreen(activity)
//}