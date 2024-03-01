package ru.pakarpichev.randomuserapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pakarpichev.randomuserapp.data.repository.DataRepositoryImpl
import ru.pakarpichev.randomuserapp.domain.repository.DataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindRepository {
    @Binds
    @Singleton
    abstract fun bindRemoteDataRepository(
        remoteDataRepositoryImpl: DataRepositoryImpl
    ): DataRepository
}