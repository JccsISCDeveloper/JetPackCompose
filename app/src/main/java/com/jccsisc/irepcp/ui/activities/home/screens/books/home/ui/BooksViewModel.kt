package com.jccsisc.irepcp.ui.activities.home.screens.books.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.core.constants.Constants.NO_VALUE
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model.Mascota
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository.MascotaRepository
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
class BooksViewModel @Inject constructor(private val repo: MascotaRepository) : ViewModel() {
    val mascotas = repo.getMascotasFromRoom()
    var mascota by mutableStateOf(Mascota(0, NO_VALUE, NO_VALUE))

    fun addMascota(mascota: Mascota) = viewModelScope.launch(Dispatchers.IO) {
        repo.addMascotaToRoom(mascota)
    }

    fun getMascota(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        mascota = repo.getMascotaFromRoom(id)
    }

    fun updateAnimal(animal: String) {
        mascota = mascota.copy(
            animal = animal
        )
    }
    fun updateRaza(raza: String) {
        mascota = mascota.copy(
            raza = raza
        )
    }
    fun updateMascota(mascota: Mascota) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateMascotaFromRoom(mascota)
    }


    fun deleteMascota(mascota: Mascota) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteMascotaFromRoom(mascota)
        }
    }

}