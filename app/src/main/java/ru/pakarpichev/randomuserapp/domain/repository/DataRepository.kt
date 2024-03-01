package ru.pakarpichev.randomuserapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel
import ru.pakarpichev.randomuserapp.utils.Resource

interface DataRepository {

    suspend fun getData(fetchFromRemote: Boolean):Flow<Resource<List<RandomUserModel>>>

    suspend fun getPerson(modelId: Int):Flow<Resource<RandomUserModel>>
}