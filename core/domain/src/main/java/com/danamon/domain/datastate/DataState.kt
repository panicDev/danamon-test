package com.danamon.domain.datastate

sealed class DataState<T>(
    var data: T? = null,
    var stateMessage: StateMessage? = null
) {
    class SUCCESS<T>(
        data: T? = null,
        stateMessage: StateMessage? = null
    ) : DataState<T>(
        data = data,
        stateMessage = stateMessage
    )

    class ERROR<T>(
        stateMessage: StateMessage? = null
    ) : DataState<T>(
        stateMessage = stateMessage
    )
}