package com.example.imgurgalleryapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract fun getLayoutResource(): Int

    protected lateinit var viewModel: VM

    protected abstract fun createdViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createdViewModel()
        setContentView(getLayoutResource())
    }


}