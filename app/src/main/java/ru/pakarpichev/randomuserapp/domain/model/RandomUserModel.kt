package ru.pakarpichev.randomuserapp.domain.model

data class RandomUserModel(
    val id: Int? = null,
    val gender: String,
    val nameTitle: String,
    val nameFirst: String,
    val nameLast: String,
    val numberOfStreet: String,
    val nameOfStreet: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val offset: String,
    val description: String,
    val dateOfBirth: String,
    val age: String,
    val picture: String,
    val smallPicture: String,
    val email: String,
    val phoneNumber: String,
    val latitude: String,
    val longtitude: String
)