package ru.pakarpichev.randomuserapp.data.mapper

import ru.pakarpichev.randomuserapp.data.local.RandomUserEntity
import ru.pakarpichev.randomuserapp.data.remote.RandomUserDto
import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun RandomUserDto.toListRandomUserEntity(): List<RandomUserEntity> {
    val list = results.map { item ->
        RandomUserEntity(
            id = null,
            gender = item.gender,
            nameTitle = item.name.title,
            nameFirst = item.name.first,
            nameLast = item.name.last,
            numberOfStreet = item.location.street.number,
            nameOfStreet = item.location.street.name,
            city = item.location.city,
            state = item.location.state,
            country = item.location.country,
            postcode = item.location.postcode,
            offset = item.location.timezone.offset,
            description = item.location.timezone.description,
            dateOfBirth = item.dob.date,
            age = item.dob.age,
            picture = item.picture.large,
            smallPicture = item.picture.medium,
            email = item.email,
            phoneNumber = item.phone,
            latitude = item.location.coordinates.latitude,
            longtitude = item.location.coordinates.longitude
        )
    }
    return list
}

fun RandomUserEntity.toRandomUserModel(): RandomUserModel {
    val dateTime =
        LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz"))
    val birthDate = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(dateTime)
    return RandomUserModel(
        id,
        gender,
        nameTitle,
        nameFirst,
        nameLast,
        numberOfStreet,
        nameOfStreet,
        city,
        state,
        country,
        postcode,
        offset,
        description,
        dateOfBirth = birthDate,
        age,
        picture,
        smallPicture,
        email,
        phoneNumber,
        latitude,
        longtitude
    )
}
