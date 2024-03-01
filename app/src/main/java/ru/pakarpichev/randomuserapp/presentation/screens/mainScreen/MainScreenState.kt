package ru.pakarpichev.randomuserapp.presentation.screens.mainScreen

import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel

data class MainScreenState (
    val usersList: List<RandomUserModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)