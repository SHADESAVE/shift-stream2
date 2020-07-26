package com.example.shiftstream2.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shiftstream2.R
import com.example.shiftstream2.anyArgument
import com.example.shiftstream2.model.entity.CityItem
import com.example.shiftstream2.model.entity.NestedItem
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment : Fragment(R.layout.fragment_third) {

    var param1 by anyArgument()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (param1 !is NestedItem) return
        nestedText.text = (param1 as NestedItem).tittle
    }
}