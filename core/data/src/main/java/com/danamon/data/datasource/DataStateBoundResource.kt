package com.danamon.data.datasource

import com.danamon.data.base.ResponseEntity
import com.danamon.data.base.ResponseEntity.Companion.SUCCESS_RESPONSE_CODE
import com.danamon.data.mapper.ToModelMapper
import com.danamon.domain.datastate.DataState
import com.danamon.domain.datastate.StateMessage
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class DataStateBoundResource<ENTITY : ToModelMapper<MODEL>, MODEL> private constructor(
    private val networkCall: (suspend  () -> ResponseEntity<ENTITY>?)? = null
){

    suspend fun getResult(): DataState<MODEL> {
        if(networkCall != null) return safeNetworkCall()
        return createDataStateError(status = -1, message = "Error")
    }

    private suspend fun safeNetworkCall() : DataState<MODEL> {
        var dataState = createDataStateError(-1, "")
        try {
            val result = networkCall?.invoke() ?: return dataState
            dataState = handleDataResponse(result)
        } catch (throwable: SocketTimeoutException) {
            dataState = createDataStateError(status = -1)
        } catch (throwable: CancellationException) {
            dataState = createDataStateError(status = 502)
        } catch (throwable: ConnectException) {
            dataState = createDataStateError(status = 502)
        } catch (throwable: UnknownHostException) {
            dataState = createDataStateError(status = 404)
        } catch (throwable: HttpException) {
            val code = getHttpCode(throwable)
            dataState = createDataStateError(status = code)
        }
        return dataState
    }

    private fun getHttpCode(throwable: HttpException): Int {
        return try {
            throwable.response()?.code() ?: -1
        } catch (e: Exception) {
            -1
        }
    }

    private fun handleDataResponse(response: ResponseEntity<ENTITY>): DataState<MODEL> {
        return when(response.status == SUCCESS_RESPONSE_CODE) {
            true -> processSuccess(response.data)
            false -> processNetworkError(response)
        }
    }

    private fun processSuccess(entity: ENTITY?) = DataState.SUCCESS(entity?.toModel())

    private fun processNetworkError(response: ResponseEntity<ENTITY>) =
        createDataStateError(
            status = response.status,
            message = response.message
        )

    private fun createDataStateError(status: Int? = 0, message: String? = ""): DataState<MODEL> {
        return DataState.ERROR(
            StateMessage(status = status, message = message)
        )
    }

    companion object {
        fun <ENTITY : ToModelMapper<MODEL>, MODEL> createNetworkCall(
            networkCall: (suspend () -> ResponseEntity<ENTITY>?)
        ) : DataStateBoundResource<ENTITY, MODEL> = DataStateBoundResource(
            networkCall = networkCall
        )
    }

}