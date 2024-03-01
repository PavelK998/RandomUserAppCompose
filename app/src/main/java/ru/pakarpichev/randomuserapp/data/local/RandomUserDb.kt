package ru.pakarpichev.randomuserapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RandomUserEntity::class], version = 1)
abstract class RandomUserDb : RoomDatabase() {
    abstract val dao: RandomUserDao
}