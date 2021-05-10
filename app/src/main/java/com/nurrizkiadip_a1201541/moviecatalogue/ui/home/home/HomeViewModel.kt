package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.home

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nurrizkiadip_a1201541.moviecatalogue.data.Repository

class HomeViewModel constructor(val repository: Repository) : ViewModel() {
    private var _application: Application? = null
    val application: Application
        get() {
            return _application as Application
        }

    fun setApplication(application: Application) {
        this._application = application
    }




}