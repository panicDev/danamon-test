package com.danamon.test.feature.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danamon.common.base.viewmodel.BaseViewModel
import com.danamon.common.utils.StateWrapper
import com.danamon.domain.model.User
import com.danamon.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): BaseViewModel() {

    sealed class Event {
        data class UserLogin(var email: String, var password: String, var role: String) : Event()
    }

    sealed class State {
        data class NavigateToDashboard(var success: Boolean) : State()
        data class ShowLoading(var isLoading: Boolean) : State()
    }

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    fun onEvent(event: Event){
        when(event) {
            is Event.UserLogin -> processLoginUser(event.email, event.password, event.role)
        }
    }

    private fun processLoginUser(email: String, password: String, role: String) {
        viewModelScope.launch {
            val data = userUseCase.invokeLogin(email, password, role)
            userUseCase.invokeSetUserRole(role)
            userUseCase.invokeSetUserIsLoggedIn(true)

            setState(State.ShowLoading(true))
            delay(300)

            setState(State.ShowLoading(false))
            setState(State.NavigateToDashboard(
                success =
                data.id != null &&
                !data.email.isNullOrEmpty() &&
                !data.username.isNullOrEmpty() &&
                !data.role.isNullOrEmpty()
            ))
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }

}