package ru.pakarpichev.randomuserapp.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import ru.pakarpichev.randomuserapp.domain.repository.OpenThirdPartyAppsRepository
import javax.inject.Inject

class OpenThirdPartyAppsRepositoryImpl @Inject constructor(
    private val context: Context
): OpenThirdPartyAppsRepository {
    override suspend fun openPhone(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        ContextCompat.startActivity(context, intent, null)
    }

    override suspend fun openMaps(latitude: String, longtitude: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = Uri.parse("geo:$latitude, $longtitude")
        ContextCompat.startActivity(context, intent, null)
    }

    override suspend fun sendEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = Uri.fromParts("mailto",email, null)
        ContextCompat.startActivity(context, intent, null)
    }
}