package com.example.vmmbmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vmmbmicalculator.ui.theme.VmmBmiCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VmmBmiCalculatorTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Bmi(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Bmi(modifier: Modifier = Modifier, bmiViewModel: BmiViewModel = viewModel()) {
    Column {
        Text (
            text = stringResource(R.string.body_mass_index),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        OutlinedTextField(
            value = bmiViewModel.heightInput,
            onValueChange = {bmiViewModel.heightInput = it.replace(',','.')},
            label = {Text(stringResource(R.string.height))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )
        OutlinedTextField(
            value = bmiViewModel.weightInput,
            onValueChange = {bmiViewModel.weightInput = it.replace(',','.')},
            label = {Text(stringResource(R.string.weight))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        )
        Text (
            text = stringResource(R.string.result) + bmiViewModel.bmi,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        )
    }
}

@Composable
@Preview (showBackground = true)
fun BmiPreview (){
    VmmBmiCalculatorTheme {
        Bmi()
    }
}
