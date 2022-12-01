package com.jccsisc.irepcp.ui.screens.dashboard.home.data.local

import com.jccsisc.irepcp.ui.screens.dashboard.home.domain.model.Mascota
import com.jccsisc.irepcp.ui.screens.dashboard.home.domain.repository.MascotaRepository
import com.jccsisc.irepcp.ui.screens.dashboard.home.domain.repository.Mascotas
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
class MascotaRepositoryImpl(private val mascotaDao: MascotaDao): MascotaRepository {

    override fun getMascotasFromRoom(): Flow<Mascotas> = mascotaDao.getMascotas()
    override fun addMascotaToRoom(mascota: Mascota) = mascotaDao.addMascota(mascota)

}