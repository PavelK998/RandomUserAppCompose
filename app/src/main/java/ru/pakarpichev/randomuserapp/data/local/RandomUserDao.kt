package ru.pakarpichev.randomuserapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RandomUserDao {

    @Insert()
    suspend fun insertAllUserData(listModel: List<RandomUserEntity>)

    @Query("DELETE FROM random_user_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM random_user_table")
    suspend fun getAllData(): List<RandomUserEntity>

    @Query("SELECT * FROM random_user_table WHERE id = :modelId")
    suspend fun getPerson(modelId: Int): RandomUserEntity?


}