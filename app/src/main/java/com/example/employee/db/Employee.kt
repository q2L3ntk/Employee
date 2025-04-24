package com.example.employee.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class Employee (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val photo: String? = null,
    val dateBirth: String,
    val group: String,
    val position: String,
)