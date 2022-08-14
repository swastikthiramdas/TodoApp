package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var tittle : String,
    var priority : String
)