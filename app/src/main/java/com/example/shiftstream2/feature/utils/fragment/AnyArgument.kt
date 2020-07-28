package com.example.shiftstream2.feature.utils.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.shiftstream2.feature.utils.bundle.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun anyArgument(): ReadWriteProperty<Fragment, Any> =
    AnyArgument

private object AnyArgument : ReadWriteProperty<Fragment, Any> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): Any=
        requireNotNull(thisRef.requireArguments().get(property.name))

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: Any) {
        val arguments = thisRef.arguments ?: Bundle().also {
            thisRef.arguments = it
        }

        arguments.put(property.name, value)
    }
}