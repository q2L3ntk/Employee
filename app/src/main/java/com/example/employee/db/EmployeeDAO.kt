package com.example.employee.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmployeeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: Employee)

    @Update
    fun update(employee: Employee)

    @Delete
    fun delete(employee: Employee)

    @Query("SELECT * FROM employees")
    fun getAllEmployees(): List<Employee>

    @Query("DELETE FROM employees")
    fun deleteAllEmployees()
}