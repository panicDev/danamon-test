package com.danamon.test.feature.dashboard.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.danamon.common.base.viewmodel.BaseViewModel
import com.danamon.common.utils.StateWrapper
import com.danamon.domain.datastate.DataState
import com.danamon.domain.model.PhotoList
import com.danamon.domain.model.User
import com.danamon.domain.usecase.PhotoUseCase
import com.danamon.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoUseCase: PhotoUseCase,
    private val userUseCase: UserUseCase
): BaseViewModel() {

    sealed class Event {
        object UserRole: Event()
        object LoadPhotos : Event()
        object LoadAllUser : Event()
    }

    sealed class State {
        data class GetUserRole(var userRole: String) : State()
        data class ShowPhotos(var photoList: PhotoList) : State()
        data class ShowAllUser(var data: List<User>) : State()
        data class ShowLoading(val isLoading: Boolean) : State()
    }

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    fun onEvent(event: Event) {
        when(event) {
            is Event.LoadPhotos -> loadPhotos()
            is Event.LoadAllUser -> showAllUser()
            is Event.UserRole -> getUserRole()
        }
    }

    private fun getUserRole() {
        viewModelScope.launch {
            val role = userUseCase.invokeGetUserRole()
            setState(State.GetUserRole(role))
        }
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            setState(State.ShowLoading(true))
            when(val result = photoUseCase.invoke()) {
                is DataState.SUCCESS -> setState(State.ShowPhotos(result.data ?: PhotoList()))
                is DataState.ERROR -> showSnack.value = result.stateMessage?.message
            }
            setState(State.ShowLoading(false))
        }
    }

    private fun showAllUser() {
        viewModelScope.launch {
            setState(State.ShowLoading(true))
            val data = userUseCase.invokeShowListUser()
            delay(500)

            setState(State.ShowAllUser(data))
            setState(State.ShowLoading(false))
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }
}