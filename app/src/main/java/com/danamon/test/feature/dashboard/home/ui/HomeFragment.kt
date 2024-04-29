package com.danamon.test.feature.dashboard.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.danamon.common.base.BaseFragment
import com.danamon.common.ext.emptyString
import com.danamon.common.ext.subscribeSingleState
import com.danamon.domain.model.PhotoList
import com.danamon.domain.model.User
import com.danamon.test.databinding.FragmentHomeBinding
import com.danamon.test.feature.dashboard.home.adapter.PhotosAdapter
import com.danamon.test.feature.dashboard.home.adapter.UserAdapter
import com.danamon.test.feature.dashboard.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()
    override fun inflateViewBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }

    private val photosAdapter by lazy { PhotosAdapter() }
    private val userAdapter by lazy { UserAdapter() }
    var role = emptyString()

    override fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when(it) {
                is HomeViewModel.State.GetUserRole -> this.role = it.userRole
                is HomeViewModel.State.ShowPhotos -> showListOfPhotos(it.photoList)
                is HomeViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is HomeViewModel.State.ShowAllUser -> showListOfUser(it.data)
            }
        }
    }

    override fun setupView() {
        viewModel.onEvent(HomeViewModel.Event.UserRole)

        if(role == "ADMIN") viewModel.onEvent(HomeViewModel.Event.LoadAllUser)
        else viewModel.onEvent(HomeViewModel.Event.LoadPhotos)
    }

    private fun showListOfPhotos(photoList: PhotoList) = with(binding) {
        // -1 - less than, 1 - greater than, 0 - equal, all inverse for descending
        val sortedData = photoList.data.sortedWith { lhs, rhs ->
            if (lhs.albumId > rhs.albumId) -1 else if (lhs.albumId < rhs.albumId) 1 else 0
        }
        photosAdapter.data = sortedData
        rvPhotos.adapter = photosAdapter
        rvUser.visibility = View.GONE
    }

    private fun showListOfUser(userList: List<User>) = with(binding) {
        // -1 - less than, 1 - greater than, 0 - equal, all inverse for descending
        val sortedData = userList.sortedWith { lhs, rhs ->
            if (lhs.id!! > rhs.id!!) -1 else if (lhs.id!! < rhs.id!!) 1 else 0
        }
        userAdapter.data = sortedData
        rvUser.adapter = userAdapter
        rvPhotos.visibility = View.GONE
    }

    private fun showLoading(isNeedToShow: Boolean) {
        binding.progressBar.isVisible = isNeedToShow
    }

}
