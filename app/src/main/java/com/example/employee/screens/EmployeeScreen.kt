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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.composables.composetheme.ComposeTheme
import com.composables.composetheme.base
import com.composables.composetheme.buildComposeTheme
import com.composables.composetheme.colors
import com.composables.composetheme.gray600
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
fun EmployeeScreen(onClose: () -> Unit, employee: Employee) {
    val EmployeeScreen = buildComposeTheme {  }
    EmployeeScreen {
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
                        AsyncImage(
                            model = employee.photo,
                            contentDescription = employee.surname,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(color = Color.White),
                            contentScale = ContentScale.Crop,
                            placeholder = rememberVectorPainter(Icons.Filled.AccountCircle),
                            error = rememberVectorPainter(Icons.Filled.AccountCircle)
                        )
                        Spacer(Modifier.height(18.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            BasicText(
                                text = employee.name,
                                style = ComposeTheme.textStyles.base.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            )
                            BasicText(
                                text = employee.surname,
                                style = ComposeTheme.textStyles.base.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        BasicText(
                            text = employee.dateBirth,
                            style = ComposeTheme.textStyles.base.copy(
                                fontWeight = FontWeight.Medium,
                                color = ComposeTheme.colors.gray600,
                                textAlign = if (isCompact) TextAlign.Center else TextAlign.Start
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        BasicText(
                            text = employee.position,
                            style = ComposeTheme.textStyles.base.copy(
                                fontWeight = FontWeight.Medium,
                                color = ComposeTheme.colors.gray600,
                                textAlign = if (isCompact) TextAlign.Center else TextAlign.Start
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        BasicText(
                            text = employee.group,
                            style = ComposeTheme.textStyles.base.copy(
                                fontWeight = FontWeight.Medium,
                                color = ComposeTheme.colors.gray600,
                                textAlign = if (isCompact) TextAlign.Center else TextAlign.Start
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                Modifier.clip(ComposeTheme.shapes.roundL)
                                    .clickable(role = Role.Button) {
                                        onClose()
                                        true
                                    }.background(DarkViolet)
                                    .border(1.dp, DarkViolet, ComposeTheme.shapes.roundL)
                                    .padding(horizontal = 14.dp, vertical = 10.dp)
                                    .let { if (isCompact) it.weight(1f) else it },
                                contentAlignment = Alignment.Center
                            ) {
                                BasicText(
                                    text = "Назад", style = ComposeTheme.textStyles.base.copy(
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