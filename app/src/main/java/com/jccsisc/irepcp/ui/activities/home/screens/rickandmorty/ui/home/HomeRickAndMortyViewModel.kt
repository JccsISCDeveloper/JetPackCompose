package com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jccsisc.irepcp.core.MyResultS
import com.jccsisc.irepcp.ui.activities.home.screens.rickandmorty.domain.usecase.GetPersonajesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.rickandmorty.ui
 * Created by Julio Cesar Camacho Silva on 06/12/22
 */
@HiltViewModel
class HomeRickAndMortyViewModel @Inject constructor(
    private val getPersonajesUseCase: GetPersonajesUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPage = 1

    init {
        getPersonajes(increase = false)
    }

    fun getPersonajes(increase: Boolean) {
        viewModelScope.launch {
            if (increase) currentPage++ else if (currentPage > 1) currentPage--

            val showPrevious = currentPage > 1
            val showNext = currentPage < 42

            getPersonajesUseCase(currentPage).onEach { result ->
                when (result) {
                    is MyResultS.Loading -> state = state.copy(isLoading = true)
                    is MyResultS.Success -> {
                        state = state.copy(
                            personajes = result.data ?: emptyList(),
                            isLoading = false,
                            showPrevious = showPrevious,
                            showNext = showNext
                        )
                    }
                    is MyResultS.Failure -> {
                        state = state.copy(isLoading = false)
                        _eventFlow.emit(UIEvent.ShowSnackBar(result.msg ?: "Ocurrió un error"))
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val msg: String): UIEvent()
    }
}