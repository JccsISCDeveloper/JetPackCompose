package com.jccsisc.irepcp.ui.screens.dashboard.home.domain.repository

import com.jccsisc.irepcp.ui.screens.dashboard.home.domain.model.Mascota
import kotlinx.coroutines.flow.Flow

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.domain.repository
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
typealias Mascotas = List<Mascota>
interface MascotaRepository {
    fun getMascotasFromRoom() : Flow<Mascotas>
    fun addMascotaToRoom(mascota: Mascota)
}