package com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.core.constants.Constants.ID
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail
 * Created by Julio Cesar Camacho Silva on 07/12/22
 */
class DetailRickAndMortyViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    init {
        getPersonaje()
    }

    private fun getPersonaje() {
        savedStateHandle.get<Int>(ID)?.let { id ->
            viewModelScope.launch {
                Log.d("id", id.toString())
            }
        }
    }
}