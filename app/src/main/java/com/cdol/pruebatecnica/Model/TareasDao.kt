package com.cdol.pruebatecnica.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TareasDao {
    @Query("SELECT * FROM tareas")
    fun getAll():LiveData<List<Tarea>>

    @Query("SELECT * FROM tareas WHERE idTarea = :id")
    fun get(id: Int): LiveData<Tarea>

    @Insert
    fun insertAll(vararg tareas: Tarea)

    @Update
    fun update(tarea: Tarea)

    @Delete
    fun delete(tarea: Tarea)
}