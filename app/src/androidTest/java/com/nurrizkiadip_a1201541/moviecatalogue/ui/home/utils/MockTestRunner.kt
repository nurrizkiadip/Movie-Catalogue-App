package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class MockTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?,
                                context: Context?): Application {
        return super.newApplication(cl, MovieCatalogueTestApp::class.java.name, context)
    }
}