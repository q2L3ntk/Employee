package com.example.employee.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.employee.components.Notification
import com.example.employee.db.Employee
import com.example.employee.db.EmployeeDAO
import com.example.employee.ui.nav.AppBar
import com.example.employee.ui.theme.DarkViolet

@Composable
internal fun NewEmployeeContent(
    dao: EmployeeDAO,
    drawerState: DrawerState,
    notification: Notification
) {
    var nameField by remember { mutableStateOf("") }
    var surnameField by remember { mutableStateOf("") }
    var dateBirthField by remember { mutableStateOf("") }
    var positionField by remember { mutableStateOf("") }
    var groupField by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppBar(drawerState = drawerState, title = "Новый сотрудник") },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "New employee image",
                    modifier = Modifier.size(100.dp),
                    colorFilter = ColorFilter.tint(color = Color.White)
                )
            }
            Text(
                text = "Для создания нового сотрудника - заполните все данные. ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            TextField(
                value = nameField,
                onValueChange = { newText -> nameField = newText },
                modifier = Modifier
                    .width(width = 300.dp)
                    .padding(top = 16.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                supportingText = { Text(text = "Имя сотрудника", color = Color.White) }
            )
            TextField(
                value = surnameField,
                onValueChange = { newText -> surnameField = newText },
                modifier = Modifier
                    .width(width = 300.dp)
                    .padding(top = 12.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                supportingText = { Text(text = "Фамилия сотрудника", color = Color.White) }
            )
            TextField(
                value = dateBirthField,
                onValueChange = { newtText -> dateBirthField = newtText },
                modifier = Modifier
                    .width(width = 300.dp)
                    .padding(top = 12.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                supportingText = { Text(text = "Дата рождения", color = Color.White) }
            )
            TextField(
                value = positionField,
                onValueChange = { newtText -> positionField = newtText },
                modifier = Modifier
                    .width(width = 300.dp)
                    .padding(top = 12.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                supportingText = { Text(text = "Должность сотрудника", color = Color.White) }
            )
            TextField(
                value = groupField,
                onValueChange = { newtText -> groupField = newtText },
                modifier = Modifier
                    .width(width = 300.dp)
                    .padding(top = 12.dp),
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent
                ),
                supportingText = { Text(text = "Подразделение", color = Color.White) }
            )
            Button(
                onClick = {
                    val newEmployee = Employee(name = nameField, surname = surnameField, dateBirth = dateBirthField, position = positionField, group = groupField)
                    dao.insert(newEmployee)
                    notification.sendNotification("Новый сотрудник добавлен", "Сотрудник ${nameField} ${surnameField} успешно добавлен в базу")
                    nameField = ""
                    surnameField = ""
                    positionField = ""
                    dateBirthField = ""
                    groupField = ""
                },
                modifier = Modifier
                    .width(300.dp)
                    .padding(top = 12.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkViolet),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Добавить сотрудника компании", color = Color.White)
            }
        }
    }
}