package com.example.vmmbmicalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class BmiViewModel: ViewModel() {
    var heightInput: String by mutableStateOf("")
    var weightInput: String by mutableStateOf("")
    private val height: Float
        get () {
            return heightInput.toFloatOrNull() ?: 0.0f
        }
    private val weight: Int
        get() {
            return weightInput.toIntOrNull() ?: 0
        }

    private val formatter = DecimalFormat("0.00")

    val bmi: String
        get() {
            if (weight > 0 && height > 0) {
                val heightInMeters = height / 100.0
                val bmiValue =
                    weight / (heightInMeters * heightInMeters)
                return formatter.format(bmiValue)
            }
            else return  "0.0"
        }

}