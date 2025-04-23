package com.example.employee.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
                Text(
                    "Безопасность",
                    style = MaterialTheme.typography.titleMedium,
                    color = DarkViolet,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 2.dp)
                )
            }
            item {
                var checked by remember { mutableStateOf(true) }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Облачный пароль",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        thumbContent = if (checked) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize)
                                )
                            }
                        } else null
                    )
                }
            }
            item {
                var checked by remember { mutableStateOf(true) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Код-пароль",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        thumbContent = if (checked) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize)
                                )
                            }
                        } else null
                    )
                }
            }
            item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
            item {
                Text(
                    "Конфиденциальность",
                    style = MaterialTheme.typography.titleMedium,
                    color = DarkViolet,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 8.dp)
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Номер телефона",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    BasicText(
                        "Никто",
                        style = MaterialTheme.typography.titleMedium,
                        color = { DarkViolet }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Время захода",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    BasicText(
                        "Никто",
                        style = MaterialTheme.typography.titleMedium,
                        color = { DarkViolet }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Фотографии профиля",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    BasicText(
                        "Никто",
                        style = MaterialTheme.typography.titleMedium,
                        color = { DarkViolet }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicText(
                        "Дата рождения",
                        style = MaterialTheme.typography.titleMedium,
                        color = { Color.White }
                    )
                    BasicText(
                        "Никто",
                        style = MaterialTheme.typography.titleMedium,
                        color = { DarkViolet }
                    )
                }
            }
            item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
            item {
                Text(
                    "Стереть все данные",
                    style = MaterialTheme.typography.titleMedium,
                    color = DarkViolet,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 2.dp)
                )
            }
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
            item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
            item {
                Surface(color = Color.Black) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(30.dp)
                    ) {
                        Box(modifier = Modifier.size(30.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                "Версия 0.0.1 ALPHA",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(0.44f)
                            )
                            Text(
                                "© 2024 Employee Company",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(0.44f)
                            )
                        }
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