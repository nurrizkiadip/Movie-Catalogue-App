package com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote

sealed class ApiResponse<T>(val status: StatusResponse, val data: T?, val message: String?)
class SuccessResponse<T>(data: T?): ApiResponse<T>(StatusResponse.SUCCESS, data, null)
class EmptyResponse<T>(data: T?, message: String?): ApiResponse<T>(StatusResponse.EMPTY, data, message)
class ErrorResponse<T>(message: String?): ApiResponse<T>(StatusResponse.ERROR, null, message)