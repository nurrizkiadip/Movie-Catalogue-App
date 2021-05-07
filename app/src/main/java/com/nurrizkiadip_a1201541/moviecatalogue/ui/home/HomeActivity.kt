package com.nurrizkiadip_a1201541.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.nurrizkiadip_a1201541.moviecatalogue.R
import com.nurrizkiadip_a1201541.moviecatalogue.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sectionPageAdapter: SectionPageAdapter

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        sectionPageAdapter = SectionPageAdapter(this@HomeActivity.application,this@HomeActivity)
        binding.viewPager2.adapter = sectionPageAdapter

        val tabLayoutMediator = TabLayoutMediator(binding.tabs, binding.viewPager2){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }
        tabLayoutMediator.attach()
    }
}