package com.example.shiftstream2.feature.city.detail.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.shiftstream2.R
import com.example.shiftstream2.feature.utils.fragment.anyArgument
import com.example.shiftstream2.feature.city.domain.entity.Forecast
import kotlinx.android.synthetic.main.fragment_second.*

class CityDetailFragment : Fragment(R.layout.fragment_second) {

    var param1 by anyArgument()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (param1 !is Forecast) return
        cityText.text = (param1 as Forecast).name
    }
}