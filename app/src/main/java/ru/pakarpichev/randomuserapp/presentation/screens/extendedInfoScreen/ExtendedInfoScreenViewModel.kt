package ru.pakarpichev.randomuserapp.presentation.screens.extendedInfoScreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.pakarpichev.randomuserapp.domain.repository.DataRepository
import ru.pakarpichev.randomuserapp.domain.repository.OpenThirdPartyAppsRepository
import ru.pakarpichev.randomuserapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class ExtendedInfoScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val openThirdPartyAppsRepository: OpenThirdPartyAppsRepository,
    savedStateHandle: SavedStateHandle
)  : ViewModel() {
    private val _state = MutableStateFlow(ExtendedInfoScreenState())
    val state = _state.asStateFlow()
    private val modelId: String = checkNotNull(savedStateHandle["modelId"])
    init {
            getPerson(modelId.toInt())

    }

    fun onEvent (event: ExtendedScreenEvent){
        when(event){
            is ExtendedScreenEvent.OpenPhone -> openPhone(event.phoneNumber)
            is ExtendedScreenEvent.OpenMaps -> openMaps(event.latitude, event.longtitude)
            is ExtendedScreenEvent.OpenEmail -> sendEmail(event.email)
        }
    }

    private fun getPerson(modelId: Int){
        viewModelScope.launch {
            dataRepository.getPerson(modelId).collect{ result ->
                when(result){
                    is Resource.Success -> _state.update {
                        it.copy(userInfo = result.data)
                    }
                    is Resource.IsLoading -> _state.update {
                        it.copy(isLoading = result.isLoading)
                    }
                    is Resource.Error -> _state.update {
                        it.copy(error = result.message)
                    }
                }
            }
        }
    }

    private fun openPhone(phoneNumber: String){
        viewModelScope.launch {
            openThirdPartyAppsRepository.openPhone(phoneNumber)
        }
    }

    private fun sendEmail(email: String){
        viewModelScope.launch {
            openThirdPartyAppsRepository.sendEmail(email)
        }
    }

    private fun openMaps(latitude: String, longtitude: String){
        Log.d("openMaps", "openMaps: $latitude, $longtitude")
        viewModelScope.launch {
            openThirdPartyAppsRepository.openMaps(latitude, longtitude)
        }
    }
}