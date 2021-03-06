package com.example.canvart.utils

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class SharedPreferenceLiveData<T>(
    val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defValue: T
) : LiveData<T>(){

    private val preferencesChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if(key == this.key) {
                value = getValueFromPreferences(key, defValue)
            }
        }

    abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key, defValue)
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferencesChangeListener)
    }

    override fun onInactive() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferencesChangeListener)
        super.onInactive()
    }

}

class SharedPreferenceIntLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Int
) :
    SharedPreferenceLiveData<Int>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Int): Int =
        sharedPreferences.getInt(key, defValue)
}

class SharedPreferenceStringLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: String
) :
    SharedPreferenceLiveData<String>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: String): String =
        sharedPreferences.getString(key, defValue)!!
}

class SharedPreferenceBooleanLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Boolean
) :
    SharedPreferenceLiveData<Boolean>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defValue)
}

class SharedPreferenceFloatLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Float
) :
    SharedPreferenceLiveData<Float>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Float): Float =
        sharedPreferences.getFloat(key, defValue)
}

class SharedPreferenceLongLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Long
) :
    SharedPreferenceLiveData<Long>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Long): Long =
        sharedPreferences.getLong(key, defValue)
}

class SharedPreferenceStringSetLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Set<String>
) :
    SharedPreferenceLiveData<Set<String>>(sharedPrefs, key, defValue) {
    override fun getValueFromPreferences(key: String, defValue: Set<String>)
            : Set<String> =
        sharedPreferences.getStringSet(key, defValue) as Set<String>
}

fun SharedPreferences.getIntLiveData(
    key: String,
    defValue: Int
): SharedPreferenceLiveData<Int> =
    SharedPreferenceIntLiveData(this, key, defValue)

fun SharedPreferences.getStringLiveData(
    key: String,
    defValue: String
): SharedPreferenceLiveData<String> =
    SharedPreferenceStringLiveData(this, key, defValue)

fun SharedPreferences.getBooleanLiveData(
    key: String,
    defValue: Boolean
): SharedPreferenceLiveData<Boolean> =
    SharedPreferenceBooleanLiveData(this, key, defValue)

fun SharedPreferences.getFloatLiveData(
    key: String,
    defValue: Float
): SharedPreferenceLiveData<Float> =
    SharedPreferenceFloatLiveData(this, key, defValue)

fun SharedPreferences.getLongLiveData(
    key: String,
    defValue: Long
): SharedPreferenceLiveData<Long> =
    SharedPreferenceLongLiveData(this, key, defValue)

fun SharedPreferences.getStringSetLiveData(
    key: String,
    defValue: Set<String>
): SharedPreferenceLiveData<Set<String>> =
    SharedPreferenceStringSetLiveData(this, key, defValue)