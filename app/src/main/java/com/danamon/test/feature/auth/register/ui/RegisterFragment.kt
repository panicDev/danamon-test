package com.danamon.test.feature.auth.register.ui

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
import com.danamon.test.databinding.FragmentRegisterBinding
import com.danamon.test.feature.auth.login.viewmodel.LoginViewModel
import com.danamon.test.feature.auth.register.viewmodel.RegisterViewModel
import com.danamon.test.feature.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override val viewModel: RegisterViewModel by viewModels()

    @FragmentScoped
    override fun inflateViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater, container, false)
    }

    private var role: String = emptyString()

    override fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when(it) {
                is RegisterViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is RegisterViewModel.State.NavigateToDashboard -> navigateToDashboard(it.success)
            }
        }
    }

    override fun setupView() = with(binding) {
        rgRegister.setOnCheckedChangeListener { _, i ->
            val radio: RadioButton = rgRegister.findViewById(i)
            role = radio.text.toString()
        }

        btnContinue.setOnClickListener {
            val username = viewFieldName.editText?.text.toString()
            val email = viewFieldEmail.editText?.text.toString()
            val password = viewFieldPassword.editText?.text.toString()

            viewModel.onEvent(RegisterViewModel.Event.UserRegister(username, email, role, password))
        }
    }

    private fun navigateToDashboard(success: Boolean){
        if(success) {
            val intent = Intent(activity, DashboardActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    private fun showLoading(isMustShowLoading: Boolean) {
        binding.progressBar.isVisible = isMustShowLoading
    }

}
