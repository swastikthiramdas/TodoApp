package com.example.todoapp

import androidx.room.*
import androidx.room.Dao


@Dao
interface Dao {

    @Insert
    fun inserttaskroom(entity: Entity)


    @Update
    fun taskupdateroom(entity: Entity)


    @Delete
    fun deletedataroom(entity: Entity)


    @Query("Delete from To_Do")
    fun deletealltaskroom()


    @Query("Select * from To_Do")
    fun getdataroom(): List<cardInfo>
}