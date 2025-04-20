package com.example.employee.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.employee.db.Employee
import com.example.employee.ui.sheet.VerticalSheet

@SuppressLint("UnrememberedMutableState")
@Composable
internal fun EmployeeContent(employee: Employee) {
    var showSheet by remember { mutableStateOf(false) }
    if (showSheet) {
        VerticalSheet(onClose = { showSheet = false })
    }

    Surface(
        Modifier.clickable{ showSheet = true },
        color = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 10.dp, bottom = 2.dp)
        ) {
            AsyncImage(
                model = employee.photo,
                contentDescription = employee.surname,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(color = Color.White),
                contentScale = ContentScale.Crop,
                placeholder = rememberVectorPainter(Icons.Filled.AccountCircle),
                error = rememberVectorPainter(Icons.Filled.AccountCircle)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "${employee.name} ${employee.surname}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = employee.position, color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun EmployeeContentPreview() {
    EmployeeContent(
        employee = Employee(
            id = 1, name = "Иван", surname = "Иванов", photo = "", position = "Техник"
        )
    )
}