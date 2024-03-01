package ru.pakarpichev.randomuserapp.presentation.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.pakarpichev.randomuserapp.domain.repository.DataRepository
import ru.pakarpichev.randomuserapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        getData(false)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.RefreshResults -> {
                getData(true)
            }
        }
    }

    private fun getData(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            repository.getData(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(usersList = result.data)
                        }
                    }
                    is Resource.IsLoading -> {
                        _state.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(error = result.message)
                        }
                    }
                }
            }
        }
    }
}