package com.example.make_my_package.view.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.make_my_package.R
import com.example.make_my_package.view.details.components.ApartmentSizeSelector
import com.example.make_my_package.view.details.components.QuantitySelector

@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {

    val context = LocalContext.current
    viewModel.loadPackageModelFromRaw(context, R.raw.data)
    val packageModel = viewModel.packageModel.value
    val totalPrice = viewModel.totalPrice.value
    val quantity = viewModel.quantity.value

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            item {
                packageModel?.let {
                    ApartmentSizeSelector(viewModel = viewModel)
                }
            }
        }

        BottomSummarySection(quantity = quantity, totalPrice = totalPrice, viewModel = viewModel)
    }
}

@Composable
fun BottomSummarySection(quantity: Int, totalPrice: Int, viewModel: DetailsViewModel) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { newQuantity ->
                    viewModel.updateQuantity(newQuantity)
                }
            )

            Button(onClick = {}) {
                Text(text = "Add to cart - ₹$totalPrice")
            }
        }
    }
}