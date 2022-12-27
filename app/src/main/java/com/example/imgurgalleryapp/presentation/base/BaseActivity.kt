package com.example.imgurgalleryapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imgurgalleryapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract fun getLayoutResource(): Int

    protected lateinit var viewModel: VM

    protected abstract fun createdViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createdViewModel()
        setContentView(getLayoutResource())
    }

    open fun showAlert(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.app_name)
            .setMessage(message)
            .setPositiveButton(resources.getString(R.string.label_ok)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}