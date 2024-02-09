package com.example.exchangeratecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun CalculateScreen(apiService: ApiService) {
    var price by remember{ mutableStateOf("")}
    var ratesData by remember{ mutableStateOf<ApiData?>(null) }

    Column(modifier= Modifier
        .background(Color(0xFF6495ED))
        .fillMaxSize()
        .padding(vertical = 17.dp, horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

    )
    {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Price Exchange Calculator",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White

        )
        Spacer(modifier = Modifier.height(30.dp))

        Row {
            ExchangeRateDropDown(apiService)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeRateDropDown(apiService:ApiService) {

    var price by remember { mutableStateOf("") }
    var ratesData by remember { mutableStateOf<ApiData?>(null) }

    var isExpanded by remember { mutableStateOf(false) }

    var rates = listOf(
        "AED", "AUD", "AZN", "BGN", "CAD", "CHF", "CNY", "DKK", "EUR",
        "GBP", "IRR", "JPY", "KWD", "NOK", "PKR", "QAR",
        "RON", "RUB", "SAR", "SEK", "USD"
    )

    var selectedRate = remember {
        mutableStateOf(rates[0])
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Select exchange rate:",
            color = Color(0xFF0C1B77),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = selectedRate.value, color = Color(0xFF0C1B77), fontSize = 20.sp)//dropdown txt
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                }
            ) {
                rates.forEach { rate ->
                    DropdownMenuItem(
                        text = { Text(text = rate) },
                        onClick = {
                            selectedRate.value = rate
                            isExpanded = false
                        }
                    )

                }

            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 19.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Enter price(₺)") },

            )
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        ratesData = apiService.getexchangeRates()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0C1B77))


            ) {
                Text("Convert")
            }
            Spacer(modifier = Modifier.height(20.dp))

            ratesData?.let {
                var selectedRateAmount =
                    it.ratesItems[0].getRateName(selectedRate = selectedRate.value)?.toFloatOrNull() // menü icinde secilen kur adı icin hessaplama yapıoy
                var calculatedPrice = "%.2f".format(price.toInt() / selectedRateAmount!!)
                //Text(text = "Price  in ${selectedRate.value} : ${calculatedPrice} ")
                if (selectedRateAmount != null) {
                    Text(
                        text = " $calculatedPrice ${selectedRate.value} ",
                        fontSize = 20.sp,
                        color = Color(0xFF0C1B77),
                        modifier = Modifier.fillMaxSize()

                    )
                }
            }
        }

    }


}






