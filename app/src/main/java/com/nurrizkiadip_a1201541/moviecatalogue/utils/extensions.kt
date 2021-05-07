package com.nurrizkiadip_a1201541.moviecatalogue.utils

import android.view.View

fun String.toEngLang(): String{
    return when(this){
        "en" -> "English"
        "ja" -> "Japan"
        "ru" -> "Russian"
        else -> this
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
