package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.core.MyResultS
import com.jccsisc.irepcp.core.constants.Constants.ID
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.usecase.GetPersonajeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui.detail
 * Created by Julio Cesar Camacho Silva on 07/12/22
 */
@HiltViewModel
class DetailRickAndMortyViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPersonajeUseCase: GetPersonajeUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getPersonaje()
    }

    private fun getPersonaje() {
        savedStateHandle.get<Int>(ID)?.let { id ->
            viewModelScope.launch {
                getPersonajeUseCase(id).also { result ->
                    when (result) {
                        is MyResultS.Loading -> state = state.copy(isLoading = true)
                        is MyResultS.Success -> state = state.copy(
                            personaje = result.data,
                            isLoading = false
                        )
                        is MyResultS.Failure -> {
                            state = state.copy(isLoading = false)
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    result.msg ?: "Ocurrió un error al descargar los datos."
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val msg: String) : UIEvent()
    }
}