package com.example.employee.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.ChevronRight
import com.composables.icons.lucide.Lucide
import com.example.employee.FAQActivity
import com.example.employee.ui.theme.DarkViolet

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FAQScreen(activity: Activity) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Частые вопросы",
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
                var expanded by remember { mutableStateOf(false) }
                val degrees by animateFloatAsState(if (expanded) -90f else 90f)

                Column {
                    Row(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { expanded = expanded.not() }
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BasicText(
                            "Как внести изменения\nв информацию о сотруднике?",
                            style = MaterialTheme.typography.titleMedium,
                            color = { Color.White }
                        )
                        Image(
                            Lucide.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.rotate(degrees),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically(
                            spring(
                                stiffness = Spring.StiffnessMediumLow,
                                visibilityThreshold = IntSize.VisibilityThreshold
                            )
                        ),
                        exit = shrinkVertically()
                    ) {
                        Box(Modifier.fillMaxWidth().padding(16.dp)) {
                            BasicText(
                                "Чтобы изменить информацию о сотруднике необходимо:\n\n" +
                                        "1. Открыть навигационную панель\n" +
                                        "2. Выбрать \"Все сотрудники\"\n" +
                                        "3. Кликнуть на необходимого сотрудника\n" +
                                        "4. В появившемся окне нажать \"Редактировать\" и изменить информацию",
                                style = MaterialTheme.typography.bodySmall,
                                color = { Color.White }
                            )
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                    )
                }
            }
            item {
                var expanded by remember { mutableStateOf(false) }
                val degrees by animateFloatAsState(if (expanded) -90f else 90f)

                Column {
                    Row(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { expanded = expanded.not() }
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BasicText(
                            "Как добавить сотрудника?",
                            style = MaterialTheme.typography.titleMedium,
                            color = { Color.White }
                        )
                        Image(
                            Lucide.ChevronRight,
                            contentDescription = null,
                            modifier = Modifier.rotate(degrees),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically(
                            spring(
                                stiffness = Spring.StiffnessMediumLow,
                                visibilityThreshold = IntSize.VisibilityThreshold
                            )
                        ),
                        exit = shrinkVertically()
                    ) {
                        Box(Modifier.fillMaxWidth().padding(16.dp)) {
                            BasicText(
                                "Чтобы добавить нового сотрудника необходимо:\n\n" +
                                        "1. Открыть навигационную панель\n" +
                                        "2. Выбрать \"Добавить сотрудника\"\n" +
                                        "3. Ввести необходимую информацию\n\n" +
                                        "После выполнения этих действий новый сотрудник будет отображаться во вкалдке \"Все сотрудники\"",
                                style = MaterialTheme.typography.bodySmall,
                                color = { Color.White }
                            )
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun FAQScreenPreview() {
    val activity: Activity = FAQActivity()
    FAQScreen(activity)
}