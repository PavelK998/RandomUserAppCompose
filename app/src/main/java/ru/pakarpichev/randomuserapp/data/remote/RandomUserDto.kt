package ru.pakarpichev.randomuserapp.data.remote


data class RandomUserDto(
    val results: List<Results>
)

data class Results(
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val dob: Dob,
    val phone: String,
    val cell: String,
    val picture: Picture,
    val nat: String
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Location(
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val timezone: TimeZone,
    val coordinates: Coordinates

)

data class Street(
    val number: String,
    val name: String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class TimeZone(
    val offset: String,
    val description: String
)

data class Dob(
    val date: String,
    val age: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)