package com.nurrizkiadip_a1201541.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.ApiResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.ErrorResponse
import com.nurrizkiadip_a1201541.moviecatalogue.data.source.remote.StatusResponse
import com.nurrizkiadip_a1201541.moviecatalogue.vo.ErrorResource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.LoadingResource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.Resource
import com.nurrizkiadip_a1201541.moviecatalogue.vo.SuccessResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<ResultType, RequestType>(
    coroutineScope: CoroutineScope
) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = LoadingResource(null)

        @Suppress("LeakingThis")
        if(shouldFetch()){
            fetchFromNetwork()
        }
        else if (!shouldFetch()){
            coroutineScope.launch(Dispatchers.IO) {
                val sourceDb = loadFromDB()
                if(sourceDb != null){
                    result.addSource(sourceDb){
                        result.value = SuccessResource(it)
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected open fun loadFromDB(): LiveData<ResultType>? { return null }

    protected abstract fun shouldFetch(): Boolean

    protected open fun createCall(): LiveData<ApiResponse<RequestType>>? { return null }

    protected open fun generateDataFromNetwork(data: RequestType): Resource<ResultType>? { return null }

    private fun fetchFromNetwork(){
        val response = createCall()

        result.value = LoadingResource(null)

        if(response != null){
            result.addSource(response){
                result.removeSource(response)

                when(it.status){
                    StatusResponse.SUCCESS -> {
                        if(it.data != null){
                            result.value = generateDataFromNetwork(it.data) as Resource<ResultType>
                        }
                    }
                    StatusResponse.EMPTY -> {
                        if(it.data != null){
                            result.value = SuccessResource(null)
                        }

                    }
                    StatusResponse.ERROR -> {
                        result.value = ErrorResource(it.message)
                    }
                }

            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}