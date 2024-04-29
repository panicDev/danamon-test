package com.danamon.common.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.danamon.common.utils.StateWrapper

inline fun <T> LifecycleOwner.subscribeSingleState(
    liveData: LiveData<StateWrapper<T>>,
    crossinline onEventUnhandled: (T) -> Unit
) {
    liveData.observe(this) { it?.getEventIfNotHandled()?.let(onEventUnhandled) }
}