package mies.mummo.heartratelimits

import android.health.connect.datatypes.HeartRateRecord
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mies.mummo.heartratelimits.ui.theme.HeartRateLimitsTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import java.math.RoundingMode


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeartRateLimitsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HeartRateLimits(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HeartRateLimits(modifier: Modifier = Modifier) {
    var ageInput by remember { mutableStateOf("") }
    val age = ageInput.toIntOrNull() ?: 0
    val upper = if (age > 0) (220 - age) * 0.85f else 0// Upper limit
    val lower = if (age > 0) (220 - age) * 0.65f else 0// Lower limit
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = ageInput,
            onValueChange = {ageInput = it},
            label = {Text(text = stringResource(R.string.age))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text =
            stringResource(R.string.heart_rate_limits, df.format(lower), df.format(upper))  ,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeartRateLimitsPreview() {
    HeartRateLimitsTheme {
        HeartRateLimits()
    }
}