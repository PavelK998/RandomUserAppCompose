package ru.pakarpichev.randomuserapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pakarpichev.randomuserapp.data.local.RandomUserDao
import ru.pakarpichev.randomuserapp.data.local.RandomUserDb
import ru.pakarpichev.randomuserapp.data.remote.RandomUserApi
import ru.pakarpichev.randomuserapp.data.repository.OpenThirdPartyAppsRepositoryImpl
import ru.pakarpichev.randomuserapp.domain.repository.OpenThirdPartyAppsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): RandomUserApi {
        return Retrofit.Builder()
            .baseUrl(RandomUserApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): RandomUserDb {
        return Room.databaseBuilder(
            context,
            RandomUserDb::class.java,
            name = "randomUser.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(database: RandomUserDb): RandomUserDao {
        return database.dao
    }

    @Provides
    @Singleton
     fun bindOpenThirdPartyAppsRepository(
        context: Application
    ): OpenThirdPartyAppsRepository = OpenThirdPartyAppsRepositoryImpl(context)
}

