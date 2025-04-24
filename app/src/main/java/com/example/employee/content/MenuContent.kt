package com.example.employee.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.employee.components.Notification
import com.example.employee.ui.nav.AppBar

@Composable
fun MenuContent(drawerState: DrawerState, notification: Notification) {
    val listState = rememberLazyListState()
    val hasScrolled by remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset > 0
        }
    }

    Scaffold(
        topBar = { AppBar(drawerState = drawerState, title = "Что нового") },
        containerColor = Color.Black
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.widthIn(max = 600.dp),
                state = listState
            ) {
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { NewsContainer("Настройки приложения", "Реализованы основные функции настроек приложения.", Icons.Filled.Settings, notification) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { NewsContainer("Изменение цветовой палитры", "Изменена основная цветовая тема приложения", Icons.Filled.Star, notification) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { NewsContainer("Настройки и общие сведения", "Теперь есть возможность настроить приложения и прочитать информацию о нём.", Icons.Filled.Home, notification) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { NewsContainer("Реализация интерфейса в сотрудниках", "Добавлена возможность удаления/редактирования сотрудника", Icons.Filled.Face, notification) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { NewsContainer("Реализация контента по навигации", "Появилась возможность переходить к контенту приложения с помощью навигационной панели.", Icons.Filled.Build, notification) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { NewsContainer("0.0.1 Альфа релиз приложения", "Полноценная альфа версия приложения с функциями добавления и просмотра списка сотрудников.", Icons.Filled.Build, notification) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
            }
        }
    }
}

@Composable
fun NewsContainer(
    title: String,
    description: String,
    image: ImageVector,
    onClick: Notification,
) {
    Surface(
        onClick = { onClick.sendNotification(title, description) },
        shape = MaterialTheme.shapes.small,
        color = Color.Black
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().size(150.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Image(
                    imageVector = image,
                    contentDescription = "New employee image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(vertical = 8.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(0.44f)
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun NewsContainerPreview() {
//    NewsContainer("0.0.1 АЛЬФА РЕЛИЗ ПРИЛОЖЕНИЯ", "", Icons.Filled.Build)
//}