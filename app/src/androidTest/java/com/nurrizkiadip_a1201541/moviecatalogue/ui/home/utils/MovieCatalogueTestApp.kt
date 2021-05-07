package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.utils

import com.nurrizkiadip_a1201541.moviecatalogue.utils.MovieCatalogueApp

class MovieCatalogueTestApp: MovieCatalogueApp() {
    private val url = "http://127.0.0.1:8080"

    override fun getBaseUrl(): String {
        return url
    }
}