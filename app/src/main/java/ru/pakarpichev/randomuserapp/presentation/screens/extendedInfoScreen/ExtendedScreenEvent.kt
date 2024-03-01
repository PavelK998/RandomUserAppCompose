package ru.pakarpichev.randomuserapp.presentation.screens.extendedInfoScreen

sealed class ExtendedScreenEvent {
    data class OpenPhone(val phoneNumber: String): ExtendedScreenEvent()
    data class OpenEmail(val email: String): ExtendedScreenEvent()
    data class OpenMaps(val latitude: String, val longtitude: String): ExtendedScreenEvent()
}