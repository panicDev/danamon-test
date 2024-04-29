package com.danamon.common.base.viewmodel

import androidx.lifecycle.ViewModel
import com.danamon.common.base.NavigationCommand
import com.danamon.common.utils.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {
    var isLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    var showToast: SingleLiveEvent<String> = SingleLiveEvent()
    var showSnack: SingleLiveEvent<String> = SingleLiveEvent()
    var navigationCommand: SingleLiveEvent<NavigationCommand> = SingleLiveEvent()
}