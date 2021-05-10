package com.nurrizkiadip_a1201541.moviecatalogue.vo

sealed class Resource <T> (val status: Status, val data: T?, val message: String?)
class SuccessResource<T>(data: T?): Resource<T>(Status.SUCCESS, data, null)
class LoadingResource<T>(data: T?): Resource<T>(Status.LOADING, data, null)
class ErrorResource<T>(message: String?): Resource<T>(Status.ERROR, null, message)
