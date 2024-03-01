package ru.pakarpichev.randomuserapp.utils

sealed class Resource<T>(val data:T? = null, val message: String? = null ) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String?): Resource<T>(data = null, message)
    class IsLoading<T>(val isLoading: Boolean = true):Resource<T>(data = null)

}