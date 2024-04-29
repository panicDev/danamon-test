package com.danamon.test.feature.auth.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danamon.common.base.viewmodel.BaseViewModel
import com.danamon.common.utils.StateWrapper
import com.danamon.domain.usecase.UserUseCase
import com.danamon.test.feature.auth.login.viewmodel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): BaseViewModel() {

    sealed class Event {
        data class UserRegister(
            var username: String,
            var email: String,
            var role: String,
            var password: String
        ) : Event()
    }

    sealed class State {
        data class NavigateToDashboard(var success: Boolean) : State()
        data class ShowLoading(var isLoading: Boolean) : State()
    }

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    fun onEvent(event: Event){
        when(event) {
            is Event.UserRegister -> registerUser(event.username, event.email, event.role, event.password)
        }
    }

    private fun registerUser(username: String, email: String, role: String, password: String) {
        viewModelScope.launch {
            userUseCase.invokeRegister(username, email, role, password)
            setState(State.ShowLoading(true))
            delay(300)

            setState(State.NavigateToDashboard(true))
            setState(State.ShowLoading(false))
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }

}