package com.example.shiftstream2.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shiftstream2.R
import com.example.shiftstream2.presentation.ui.weather.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState != null) {
            return
        }

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MainFragment()
        ).commit()
    }
}