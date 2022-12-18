package com.example.imgurgalleryapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imgurgalleryapp.data.repository.ImageRepository


class HomeViewModelFactory(
    private val repository: ImageRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}