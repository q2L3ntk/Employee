package com.example.employee.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.employee.models.MenuViewModel
import com.example.employee.ui.nav.AppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(
    drawerState: DrawerState,
    activity: Activity,
    viewModel: MenuViewModel
) {
    val listState = rememberLazyListState()
    val hasScrolled by remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset > 0
        }
    }

    Scaffold(
        topBar = { AppBar(drawerState = drawerState, title = "О приложении") },
        containerColor = Color.Black
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.widthIn(max = 600.dp),
                state = listState
            ) {
                item { AppDescription(title = "Employee for Android", description = "Универсальное приложение для работы с информацией о сотрудниках для платформы Android, написанное на Kotlin.") }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { CategoryItem(title = "Версия: 0.0.1 ALPHA", icon = Icons.Outlined.Build, onClick = { /*TODO*/ }) }
                item { CategoryItem(title = "Лицензионное соглашение", icon = Icons.Outlined.CheckCircle, onClick = { /*TODO*/ }) }
                item { CategoryItem(title = "База данных: Room v2.6.1", icon = Icons.Outlined.Warning, onClick = { /*TODO*/ }) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { CategoryItem(title = "Исходный код", icon = Icons.Outlined.Info, onClick = { viewModel.openLink("https://github.com/q2L3ntk/Employee", activity) }) }
                item { CategoryItem(title = "Telegram", icon = Icons.AutoMirrored.Outlined.Send, onClick = { viewModel.openLink("https://t.me/andprik", activity) }) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { AppDescription(
                    title = "Лицензия",
                    description =
                        "Copyright (C) 2025 by q2l3ntk\n " +
                        "<zurskij@gmail.com>\n\n" +
                        "This program is free software: you can\n" +
                        "redistribute it and/or modify it under\n" +
                        "the terms of the GNU General Public License\n" +
                        "as published by the Free Software Foundation,\n" +
                        "either version 3 of the License,\n" +
                        "or (at your option) any later version.\n\n" +
                        "This program is distributed in the hope\n" +
                        "that it will be useful, but WITHOUT ANY WARRANTY;\n" +
                        "without even the implied warranty of\n" +
                        "MERCHANTABILITY or FITNESS FOR A PARTICULAR\n" +
                        "PURPOSE\n" +
                        "See the GNU General Public License for more details.\n\n" +
                        "You should have received a copy of the\n" +
                        "GNU General Public License along with this program.\n" +
                        "If not, see <http://www.gnu.org/licenses/>"
                    )
                }
            }
        }
    }
}

