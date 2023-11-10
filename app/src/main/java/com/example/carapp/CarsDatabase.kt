package com.example.carapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"
private const val CAR_SHARED_PREF = " CAR_SHARED_PREF"

class CarsDatabase (
        private val context: Context
    ) {
        private val sharedPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
        )

        fun getAllCar(): List<CarModels> {
            val gson = Gson()
            val json = sharedPreferences.getString(CAR_SHARED_PREF,null)
            val type: Type = object : TypeToken<ArrayList<CarModels?>?>() {}.type
            val carList = gson.fromJson<List<CarModels>>(json, type)
            return carList ?: emptyList()
        }

        fun saveCar(carModel: CarModels) {
            val notes = getAllCar().toMutableList()
            notes.add(0, carModel)
            val notesGson = Gson().toJson(notes)
            sharedPreferences
                .edit()
                .putString(CAR_SHARED_PREF, notesGson)
                .apply()
        }

    fun deleteAllNotes() = sharedPreferences.edit().clear().apply()

    fun deleteCarAtIndex(index: Int) {
        val getAllCars = getAllCar().toMutableList()
        if (index in 0 until getAllCars.size) {
            getAllCars.removeAt(index)
            val carGsonString = Gson().toJson(getAllCars)
            sharedPreferences.edit().putString(CAR_SHARED_PREF, carGsonString).apply()
        }
    }
}