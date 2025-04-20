package com.example.employee.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employee.FAQActivity
import com.example.employee.models.MenuViewModel
import com.example.employee.ui.nav.AppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
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
        topBar = { AppBar(drawerState = drawerState, title = "Настройки") },
        containerColor = Color.Black
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            LazyColumn(
                contentPadding = padding,
                modifier = Modifier.widthIn(max = 600.dp),
                state = listState
            ) {
                item { CategoryItem(title = "Учетная запись", icon = Icons.Outlined.AccountCircle, onClick = { viewModel.launchAccountScreen(activity) }) }
                item { CategoryItem(title = "Приватность", icon = Icons.Outlined.Lock, onClick = { /*TODO*/ }) }
                item { CategoryItem(title = "Уведомления", icon = Icons.Outlined.Notifications, onClick = { /*TODO*/ }) }
                item { CategoryItem(title = "Пожертвовать", icon = Icons.Rounded.FavoriteBorder, onClick = { /*TODO*/ }) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { CategoryItem(title = "FAQ", icon = Icons.Outlined.Info, onClick = { viewModel.launchFAQScreen(activity) }) }
                item { CategoryItem(title = "Обратная связь", icon = Icons.Outlined.Email, onClick = { /*TODO*/ }) }
                item { CategoryItem(title = "Недавние обновления", icon = Icons.Outlined.DateRange, onClick = { /*TODO*/ }) }
                item { HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp)) }
                item { AppVersion(versionText = "Версия 0.0.1 ALPHA", copyrights = "© 2024 Employee Company", onClick = { /*TODO*/ }) }
            }
        }
    }
}

@Composable
fun CategoryItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        color = Color.Black
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )
            Text(title, style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
    }
}

@Composable
fun AppVersion(versionText: String, copyrights: String, onClick: () -> Unit) {
    Surface(onClick = onClick, color = Color.Black) {
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
                    versionText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(0.44f)
                )
                Text(
                    copyrights,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(0.44f)
                )
            }
        }
    }
}

@Composable
fun AppDescription(title: String, description: String) {
    Surface(
        color = Color.Black
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Box(modifier = Modifier.size(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    title,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White.copy(),
                )
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy()
                )
            }
        }
    }
}

@Preview
@Composable
fun AppVersionPreview() {
    AppVersion(versionText = "Text", copyrights = "copyrights", onClick = { /*TODO*/ })
}
@Preview
@Composable
fun AppDescriptionPreview() {
    AppDescription(title = "Text", description = "copyrights")
}