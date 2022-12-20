package com.jccsisc.irepcp.ui.activities.home.screens.books.mascotashome.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_MASCOTA
import com.jccsisc.irepcp.ui.activities.home.screens.books.mascotashome.domain.model.Mascota
import com.jccsisc.irepcp.ui.activities.home.screens.books.mascotashome.domain.repository.Mascotas
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Dao
interface MascotaDao {
    @Query("SELECT * FROM $TBL_MASCOTA ORDER BY id ASC")
    fun getMascotas(): Flow<Mascotas>

    @Query("SELECT * FROM $TBL_MASCOTA WHERE id = :id")
    fun getMascota(id: Int): Mascota

    @Insert(onConflict = IGNORE)
    fun addMascota(mascota: Mascota)

    @Update
    fun updateMascota(mascota: Mascota)

    @Delete
    fun deleteMascota(mascota: Mascota)
}