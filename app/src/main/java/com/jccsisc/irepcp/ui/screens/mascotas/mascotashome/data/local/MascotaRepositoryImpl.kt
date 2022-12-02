package com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.data.local

import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.model.Mascota
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.repository.MascotaRepository
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.repository.Mascotas
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
class MascotaRepositoryImpl(private val mascotaDao: MascotaDao): MascotaRepository {

    override fun getMascotasFromRoom(): Flow<Mascotas> = mascotaDao.getMascotas()
    override fun addMascotaToRoom(mascota: Mascota) = mascotaDao.addMascota(mascota)
    override fun deleteMascotaFromRoom(mascota: Mascota) = mascotaDao.deleteMascota(mascota)

}