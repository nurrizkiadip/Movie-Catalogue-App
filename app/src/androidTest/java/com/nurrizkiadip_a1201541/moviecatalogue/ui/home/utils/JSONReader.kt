package com.nurrizkiadip_a1201541.moviecatalogue.ui.home.utils

import androidx.test.platform.app.InstrumentationRegistry
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object JSONReader {
    fun read(filename: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MovieCatalogueTestApp).assets.open(filename)
            return inputStreamToString(inputStream)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    private fun inputStreamToString(inputStream: InputStream): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}