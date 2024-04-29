package com.danamon.test.feature.auth

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.danamon.common.base.BaseActivity
import com.danamon.common.ext.subscribeSingleState
import com.danamon.test.databinding.ActivityAuthenticationBinding
import com.danamon.test.feature.auth.register.AuthViewModel
import com.danamon.test.feature.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity: BaseActivity<ActivityAuthenticationBinding, AuthViewModel>() {

    override fun inflateViewBinding(layoutInflater: LayoutInflater): ActivityAuthenticationBinding {
        return ActivityAuthenticationBinding.inflate(layoutInflater)
    }

    override val viewModel: AuthViewModel by viewModels()

    override fun setupView() {
        viewModel.onEvent(AuthViewModel.Event.GetIsUserLogin)
    }

    override fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when(it) {
                is AuthViewModel.State.NavigateToDashboard -> checkIsLoggedIn(it.isLoggedIn)
            }
        }
    }

    private fun checkIsLoggedIn(isLoggedIn: Boolean) {
        if (isLoggedIn) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}
