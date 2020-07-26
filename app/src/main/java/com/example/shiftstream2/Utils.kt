package com.example.shiftstream2

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.example.shiftstream2.model.entity.CityItem
import com.example.shiftstream2.model.entity.NestedItem
import java.io.Serializable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Fragment.anyArgument(): ReadWriteProperty<Fragment, Any> = anyArgument

private object anyArgument : ReadWriteProperty<Fragment, Any> {

    override fun getValue(thisRef: Fragment, property: KProperty<*>): Any=
        requireNotNull(thisRef.requireArguments().get(property.name))

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: Any) {
        val arguments = thisRef.arguments ?: Bundle().also {
            thisRef.arguments = it
        }

        arguments.put(property.name, value)
    }
}

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is CityItem -> putSerializable(key, value)
        is NestedItem -> putSerializable(key, value)
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

fun <T> diffNotifyChanges(
    oldList: List<T>,
    newList: List<T>,
    compare: (T, T) -> Boolean
): DiffUtil.DiffResult =
    DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            compare(oldList[oldItemPosition], newList[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
    })