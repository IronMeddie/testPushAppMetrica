package com.example.applicationpush.ui.screens.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val currency = viewModel.currency.collectAsState().value

    if (currency != null){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Успех", style = MaterialTheme.typography.headlineMedium)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = currency.data.ADA.code)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = currency.data.ADA.value.toString())
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = currency.data.USD.code)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = currency.data.USD.value.toString())
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = currency.data.RUB.code)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = currency.data.RUB.value.toString())
            }
        }
        } else{
            Text(text = "Null получен при запросе", style = MaterialTheme.typography.headlineMedium)
        }






}

