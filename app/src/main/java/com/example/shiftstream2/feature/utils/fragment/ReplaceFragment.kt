package com.example.shiftstream2.feature.utils.fragment

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.city.detail.presentation.CityDetailFragment
import com.example.shiftstream2.feature.city.thirdscreen.presentation.ThirdFragment

fun replaceFragment(activity: FragmentActivity, fragment: Fragment, body: Any) {
    activity.supportFragmentManager.beginTransaction().replace(
        R.id.fragment_container,
        fragment
            .also {
                if (it is ThirdFragment)
                    it.param1 = body
                if (it is CityDetailFragment)
                    it.param1 = body
            }
    ).addToBackStack(null).commit()
}