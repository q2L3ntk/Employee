package com.example.employee.screens

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.composables.composetheme.ComposeTheme
import com.composables.composetheme.base
import com.composables.composetheme.buildComposeTheme
import com.composables.composetheme.roundL
import com.composables.composetheme.shapes
import com.composables.composetheme.textStyles
import com.composables.core.Dialog
import com.composables.core.DialogPanel
import com.composables.core.Scrim
import com.composables.core.rememberDialogState
import com.example.employee.db.Employee
import com.example.employee.db.EmployeeDAO
import com.example.employee.ui.theme.DarkViolet
import kotlinx.coroutines.delay

@Composable
fun UpdateScreen(employee: Employee, dao: EmployeeDAO, onClose: () -> Unit) {
    val UpdateScreen = buildComposeTheme { }
    UpdateScreen {
        val nameField = remember { mutableStateOf(employee.name) }
        val surnameField = remember { mutableStateOf(employee.surname) }
        val dateBirthField = remember { mutableStateOf(employee.dateBirth) }
        val position = remember { mutableStateOf(employee.position) }
        val group = remember { mutableStateOf(employee.group) }
        val state = rememberDialogState(visible = true)

        LaunchedEffect(state.visible) {
            if (state.visible.not()) {
                delay(1000)
                state.visible = true
            }
        }

        BoxWithConstraints {
            val isCompact = maxWidth <= 600.dp

            Dialog(state) {
                Scrim(
                    enter = fadeIn(),
                    exit = fadeOut(),
                    scrimColor = Color.Black.copy(0.3f),
                    modifier = Modifier
                        .pointerInput(onClose) { detectTapGestures { onClose() } }
                        .semantics(mergeDescendants = true) {
                            contentDescription = ""
                            onClick {
                                onClose()
                                true
                            }
                        }
                )
                DialogPanel(
                    modifier = Modifier.systemBarsPadding()
                        .padding(16.dp)
                        .shadow(8.dp, MaterialTheme.shapes.medium)
                        .background(Color.DarkGray, MaterialTheme.shapes.medium)
                        .padding(24.dp),
                    enter = scaleIn(initialScale = 0.8f) + fadeIn(tween(durationMillis = 250)),
                    exit = scaleOut(targetScale = 0.6f) + fadeOut(tween(durationMillis = 150))
                ) {
                    Column(
                        modifier = Modifier.let {
                            if (isCompact) it.fillMaxWidth() else it.widthIn(min = 280.dp, max = 520.dp)
                        },
                        horizontalAlignment = if (isCompact) Alignment.CenterHorizontally else Alignment.Start
                    ) {
                        TextField(
                            value = nameField.value,
                            onValueChange = { nameField.value = it },
                            label = { Text("Имя") },
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
                            )
                        )
                        Spacer(Modifier.height(10.dp))
                        TextField(
                            value = surnameField.value,
                            onValueChange = { surnameField.value = it },
                            label = { Text("Фамилия") },
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
                            )
                        )
                        Spacer(Modifier.height(10.dp))
                        TextField(
                            value = dateBirthField.value,
                            onValueChange = { dateBirthField.value = it },
                            label = { Text("Дата рождения") },
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
                            )
                        )
                        Spacer(Modifier.height(10.dp))
                        TextField(
                            value = position.value,
                            onValueChange = { position.value = it },
                            label = { Text("Должность") },
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
                            )
                        )
                        Spacer(Modifier.height(10.dp))
                        TextField(
                            value = group.value,
                            onValueChange = { group.value = it },
                            label = { Text("Подразделение") },
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
                            )
                        )
                        Spacer(Modifier.height(18.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                Modifier.clip(ComposeTheme.shapes.roundL)
                                    .clickable(role = Role.Button) {
                                        onClose()
                                        true
                                    }.background(Color.Black)
                                    .border(1.dp, Color.Black, ComposeTheme.shapes.roundL)
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .let { if (isCompact) it.weight(1f) else it },
                                contentAlignment = Alignment.Center
                            ) {
                                BasicText(
                                    text = "Отмена", style = ComposeTheme.textStyles.base.copy(
                                        color = Color.White, fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                            Box(
                                Modifier.clip(ComposeTheme.shapes.roundL)
                                    .clickable(role = Role.Button) {
                                        val updatedEmployee = employee.copy(
                                            name = nameField.value,
                                            surname = surnameField.value,
                                            position = position.value
                                        )
                                        dao.update(updatedEmployee)
                                        onClose()
                                        true
                                    }.background(DarkViolet)
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .let { if (isCompact) it.weight(1f) else it },
                                contentAlignment = Alignment.Center
                            ) {
                                BasicText(
                                    text = "Принять", style = ComposeTheme.textStyles.base.copy(
                                        color = Color.White, fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun UpdateScreen() {
//    UpdateScreen(employee = Employee(name = "Илья", surname = "Фесянов", photo = null, position = "Оператор компьютерного набора"))
//}