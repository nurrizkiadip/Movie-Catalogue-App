package com.nurrizkiadip_a1201541.moviecatalogue.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ActivitySplashScreenBinding
import com.nurrizkiadip_a1201541.moviecatalogue.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            android.R.attr.windowFullscreen,
            android.R.attr.windowFullscreen
        )

        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        binding.imageSplash.startAnimation(sideAnimation)
        binding.title.startAnimation(sideAnimation)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}