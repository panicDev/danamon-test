package com.danamon.test.feature.auth.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.danamon.common.base.BaseFragment
import com.danamon.common.ext.emptyString
import com.danamon.common.ext.subscribeSingleState
import com.danamon.test.databinding.FragmentLoginBinding
import com.danamon.test.feature.auth.login.viewmodel.LoginViewModel
import com.danamon.test.feature.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    @FragmentScoped
    override fun inflateViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater, container, false)
    }

    private var role: String = emptyString()

    override fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when (it) {
                is LoginViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is LoginViewModel.State.NavigateToDashboard -> navigateToDashboard(it.success)
            }
        }
    }

    override fun setupView() = with(binding) {
        rgLogin.setOnCheckedChangeListener { _, i ->
            val radio: RadioButton = rgLogin.findViewById(i)
            role = radio.text.toString()
        }

        btnContinue.setOnClickListener {
            val email = viewFieldEmail.editText?.text.toString()
            val password = viewFieldPassword.editText?.text.toString()
            viewModel.onEvent(LoginViewModel.Event.UserLogin(email, password, role))
        }
    }

    private fun navigateToDashboard(success: Boolean) {
        if (success) {
            val intent = Intent(activity, DashboardActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    private fun showLoading(isMustShowLoading: Boolean) {
        binding.progressBar.isVisible = isMustShowLoading
    }
}
