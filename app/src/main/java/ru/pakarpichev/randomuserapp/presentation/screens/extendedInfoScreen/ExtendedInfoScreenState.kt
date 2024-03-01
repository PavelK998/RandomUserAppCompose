package ru.pakarpichev.randomuserapp.presentation.screens.extendedInfoScreen

import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel

data class ExtendedInfoScreenState (
    val userInfo: RandomUserModel? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
)