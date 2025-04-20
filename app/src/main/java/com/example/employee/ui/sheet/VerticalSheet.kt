package com.example.employee.ui.sheet

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.core.DragIndication
import com.composables.core.ModalBottomSheet
import com.composables.core.Scrim
import com.composables.core.Sheet
import com.composables.core.SheetDetent
import com.composables.core.rememberModalBottomSheetState
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Pencil
import com.composables.icons.lucide.Trash
import kotlinx.coroutines.delay

@Composable
fun VerticalSheet(onClose: () -> Unit) {
    BoxWithConstraints {
        val isCompact = maxWidth < 600.dp
        val items = remember {
            listOf(
                Lucide.Pencil to "Редактировать",
                Lucide.Trash to "Удалить"
            )
        }
        val state = rememberModalBottomSheetState(initialDetent = SheetDetent.FullyExpanded)

        LaunchedEffect(state.isIdle) {
            if (state.isIdle && state.targetDetent == SheetDetent.Hidden) {
                delay(500)
                state.currentDetent = SheetDetent.FullyExpanded
            }
        }

        ModalBottomSheet(state) {
            Scrim(
                scrimColor = Color.Black.copy(0.3f),
                enter = fadeIn(),
                exit = fadeOut(),
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
            Sheet(Modifier
                .padding(top = 12.dp)
                .let { if (isCompact) it else it.padding(horizontal = 56.dp) }
                .statusBarsPadding()
                .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal).asPaddingValues())
                .shadow(4.dp, RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(Color.White)
                .widthIn(max = 640.dp)
                .fillMaxWidth()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.navigationBarsPadding()
                ) {
                    DragIndication(
                        modifier = Modifier.padding(top = 22.dp)
                            .background(Color.Black.copy(0.4f), MaterialTheme.shapes.large)
                            .width(32.dp)
                            .height(4.dp)
                    )
                    Spacer(Modifier.height(16.dp))
                    items.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(32.dp),
                            modifier = Modifier
                                .clickable { /*TODO*/ }
                                .clip(MaterialTheme.shapes.medium)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp)
                        ) {
                            Icon(item.first, null, tint = Color.Gray)
                            BasicText(item.second)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun VerticalSheetPreview() {
    VerticalSheet(
        onClose = { /*TODO*/ }
    )
}