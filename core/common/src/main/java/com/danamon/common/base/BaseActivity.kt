package com.danamon.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.danamon.common.base.viewmodel.BaseViewModel

abstract class BaseActivity <VB: ViewBinding, VM: BaseViewModel> : AppCompatActivity() {

    abstract fun setupView()
    abstract fun subscribeState()

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(layoutInflater: LayoutInflater): VB

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)
        setupView()
        subscribeState()
    }
}
