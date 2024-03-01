package ru.pakarpichev.randomuserapp.presentation.screens.mainScreen

sealed class MainScreenEvent {
    data object RefreshResults: MainScreenEvent()
}