package com.example.employee.content

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.employee.components.Notification
import com.example.employee.db.EmployeeDAO
import com.example.employee.models.MenuViewModel
import com.example.employee.screens.AboutScreen
import com.example.employee.screens.SettingsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MainRoute(value: String) {
    About("О приложении"),
    Settings("Настройки"),
    Main("Главная"),
    All("Все сотрудники"),
    New("Добавить сотрудника")
}

data class DrawerMenu(val icon: ImageVector, val title: String, val route: String)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, "Главная", MainRoute.Main.name),
    DrawerMenu(Icons.Filled.Face, "Все сотрудники", MainRoute.All.name),
    DrawerMenu(Icons.Filled.Add, "Добавить сотрудника", MainRoute.New.name),
    DrawerMenu(Icons.Filled.Settings, "Настройки", MainRoute.Settings.name),
    DrawerMenu(Icons.Filled.Info, "О приложении", MainRoute.About.name)
)

@Composable
fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Surface(modifier = Modifier.background(color = Color.DarkGray)) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.DarkGray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(150.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }
            Spacer(modifier = Modifier.height(12.dp).background(color = Color.DarkGray))
            menus.forEach {
                NavigationDrawerItem(
                    modifier = Modifier.background(color = Color.DarkGray),
                    label = { Text(text = it.title, color = Color.White) },
                    icon = { Icon(imageVector = it.icon, contentDescription = null, tint = Color.White) },
                    selected = false,
                    onClick = {
                        onMenuClick(it.route)
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = Color.DarkGray,
                        unselectedContainerColor = Color.DarkGray
                    )
                )
            }
        }
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    dao: EmployeeDAO,
    activity: Activity,
    viewModel: MenuViewModel,
    notification: Notification
) {
    ModalNavigationDrawer(
        modifier = Modifier.background(color = Color.DarkGray),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.DarkGray,
                drawerContentColor = Color.DarkGray
            ) {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = MainRoute.Main.name,
            modifier = Modifier.background(color = Color.DarkGray),
        ) {
            composable(MainRoute.Main.name) {
                MenuContent(drawerState = drawerState, notification = notification)
            }
            composable(MainRoute.All.name) {
                AllEmployeesContent(dao = dao, drawerState = drawerState)
            }
            composable(MainRoute.New.name) {
                NewEmployeeContent(dao = dao, drawerState = drawerState, notification = notification)
            }
            composable(MainRoute.About.name) {
                AboutScreen(drawerState, activity = activity, viewModel = viewModel)
            }
            composable(MainRoute.Settings.name) {
                SettingsScreen(drawerState, activity = activity, viewModel = viewModel)
            }
        }
    }
}