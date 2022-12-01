package com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.model.Mascota
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.repository.MascotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.ui
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@HiltViewModel
class MascotasViewModel @Inject constructor(private val repo: MascotaRepository) : ViewModel() {
    var openDialog by mutableStateOf(false)
    val mascotas = repo.getMascotasFromRoom()

    fun addMascota(mascota: Mascota) = viewModelScope.launch(Dispatchers.IO) {
        repo.addMascotaToRoom(mascota)
    }

    fun closeDialog() { openDialog = false }
    fun openDialog() { openDialog = true }
}