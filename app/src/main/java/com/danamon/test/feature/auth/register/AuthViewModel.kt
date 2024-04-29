package com.danamon.test.feature.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danamon.common.base.viewmodel.BaseViewModel
import com.danamon.common.utils.StateWrapper
import com.danamon.domain.usecase.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val userUseCase: UserUseCase): BaseViewModel() {

    sealed class Event {
        object GetIsUserLogin: Event()
    }

    sealed class State {
        data class NavigateToDashboard(var isLoggedIn: Boolean) : State()
    }

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    fun onEvent(event: Event){
        when(event) {
            is Event.GetIsUserLogin -> checkLoginState()
        }
    }

    private fun checkLoginState() {
        viewModelScope.launch {
            val isLoggedIn = userUseCase.invokeGetIsLoggedIn()
            setState(State.NavigateToDashboard(isLoggedIn))
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }


}