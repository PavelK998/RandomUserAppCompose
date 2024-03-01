package ru.pakarpichev.randomuserapp.domain.repository



interface OpenThirdPartyAppsRepository {

    suspend fun openPhone(phoneNumber: String)

    suspend fun openMaps(latitude: String, longtitude: String)

    suspend fun sendEmail (email: String)
}