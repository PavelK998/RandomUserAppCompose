package ru.pakarpichev.randomuserapp.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.pakarpichev.randomuserapp.data.local.RandomUserDao
import ru.pakarpichev.randomuserapp.data.mapper.toListRandomUserEntity
import ru.pakarpichev.randomuserapp.data.mapper.toRandomUserModel
import ru.pakarpichev.randomuserapp.data.remote.RandomUserApi
import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel
import ru.pakarpichev.randomuserapp.domain.repository.DataRepository
import ru.pakarpichev.randomuserapp.utils.Resource
import java.io.IOException
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val api: RandomUserApi,
    private val dao: RandomUserDao,
    ):DataRepository {
    override suspend fun getData(fetchFromRemote: Boolean): Flow<Resource<List<RandomUserModel>>> {
        return flow {
            emit(Resource.IsLoading(true))
            emit(Resource.Error(null))
            val localData = dao.getAllData()
            val isLocalDbEmpty = localData.isEmpty()
            val loadFromCache = !isLocalDbEmpty && !fetchFromRemote

            emit(Resource.Success(localData.map {
                it.toRandomUserModel()
            }))

            if (loadFromCache){
                emit(Resource.IsLoading(false))
                Log.d("RemoteDataRepositoryImpl", "Load from cache")
                return@flow
            }

            try {
                dao.deleteAll()
                val remoteData = api.getUser()
                remoteData?.toListRandomUserEntity()?.let {
                    dao.insertAllUserData(it)
                }
                emit(Resource.Success(dao.getAllData().map { it.toRandomUserModel() }))
                Log.d("RemoteDataRepositoryImpl", "Load from remote")
                emit(Resource.IsLoading(false))

            } catch (e: HttpException){
                Log.d("RemoteDataRepositoryImpl", "Error: ${e.localizedMessage} ")
                emit(Resource.Error("Oops, something went wrong!"))
                emit(Resource.IsLoading(false))
            } catch (e: IOException){
                Log.d("RemoteDataRepositoryImpl", "Error: ${e.localizedMessage} ")
                emit(Resource.Error("You are not connected to the Internet!"))
                emit(Resource.IsLoading(false))
            }
        }
    }

    override suspend fun getPerson(modelId: Int): Flow<Resource<RandomUserModel>> {
        return flow {
            emit(Resource.IsLoading(true))
            val localData = dao.getPerson(modelId)
            if (localData != null){
                emit(Resource.Success(localData.toRandomUserModel()))
                emit(Resource.IsLoading(false))
                Log.d("getPerson", "getPerson: Load from cache")
                return@flow
            }
            emit(Resource.Error("No data!"))
            Log.d("getPerson", "getPerson: Error")
            emit(Resource.IsLoading(false))
        }
    }
}