package com.cdol.pruebatecnica.Model

import androidx.room.*

@Dao
interface ComentarioDao {

    @Insert
    fun insert(comentario: Comentario)

    @Query("SELECT * FROM comentarios")
    fun getAllC(): List<Comentario>

    @Update
    fun updateC(comentario: Comentario)

    @Delete
    fun deletec(comentario: Comentario)

    @Query("SELECT * FROM comentarios WHERE comentario = :tarea")
    fun gettareaByC(tarea: String): List<Comentario>


}