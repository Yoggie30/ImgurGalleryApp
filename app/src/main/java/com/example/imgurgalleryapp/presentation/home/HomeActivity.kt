package com.example.imgurgalleryapp.presentation.home


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imgurgalleryapp.R
import com.example.imgurgalleryapp.data.api.ImageApi
import com.example.imgurgalleryapp.data.db.AppDatabase
import com.example.imgurgalleryapp.data.repository.ImageRepository
import com.example.imgurgalleryapp.domain.model.Image
import com.example.imgurgalleryapp.presentation.base.BaseActivity
import com.example.imgurgalleryapp.presentation.util.*
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity<HomeViewModel>(), HomeListener {
    private val imageAdapter: ImageAdapter by lazy {
        ImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setEvents()
    }

    override fun getLayoutResource() = R.layout.activity_home

    private fun init() {
        progressBar.show()
        if (NetworkUtil.isConnectedToInternet(applicationContext)) {
            viewModel.getGalleryImages("top", "week")
        } else {
            viewModel.getCachedGalleryImages()
        }
        populateListView()
    }

    private fun setEvents() {
        viewModel.homeListener = this
    }


    private fun populateListView() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = imageAdapter

    }

    private fun populateGridView() {
        val gridLayoutManager = GridLayoutManager(this, Constants.GRID_COUNT)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = imageAdapter

    }


    override fun onSuccess(imgList: List<Image>) {
        progressBar.hide()
        if (imgList.isEmpty()) {
            showAlert(getString(R.string.label_no_data_found))
        } else {
            imageAdapter.setItems(imgList)
        }
    }

    override fun onFailure(message: String) {
        progressBar.hide()
        showAlert(message)
    }

    override fun createdViewModel(): HomeViewModel {
        val api = ImageApi()
        val db = AppDatabase(this)
        val repository = ImageRepository(api, db)
        val factory = HomeViewModelFactory(repository)
        return ViewModelProviders.of(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_layout -> {
                if (item.title == getString(R.string.label_grid)) {
                    populateGridView()
                    item.title = getString(R.string.label_list)
                } else {
                    populateListView()
                    item.title = getString(R.string.label_grid)
                }
                imageAdapter.notifyItemRangeChanged(0, imageAdapter.itemCount)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}