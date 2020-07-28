package com.example.shiftstream2.feature.city.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.city.list.presentation.CitiesListFragment

class CitiesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            CitiesListFragment()
        ).commit()
    }
}