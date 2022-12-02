package com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_MASCOTA
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.model.Mascota
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.repository.Mascotas
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

    @Insert(onConflict = IGNORE)
    fun addMascota(mascota: Mascota)

    @Delete
    fun deleteMascota(mascota: Mascota)
}