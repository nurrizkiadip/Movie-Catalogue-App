package com.nurrizkiadip_a1201541.moviecatalogue.utils

import android.app.Application

open class MovieCatalogueApp: Application() {
    open fun getBaseUrl() = "https://api.themoviedb.org/"
}