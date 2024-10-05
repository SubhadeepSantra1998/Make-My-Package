package com.example.make_my_package.view.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.make_my_package.core.components.NormalBodyTextComponent
import com.example.make_my_package.view.details.DetailsViewModel

@Composable
fun SpecificationCheckboxItem(
    optionName: String,
    price: Int,
    isChecked: Boolean,
    isQuantityEnabled: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    viewModel: DetailsViewModel,
    optionId: String,
    isOptionEnabled: Boolean
) {
    val quantity = viewModel.optionQuantities.value[optionId] ?: 1

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { newCheckedState ->
                if (isOptionEnabled || !newCheckedState) {
                    onCheckedChange(newCheckedState)
                }
            },
            enabled = isOptionEnabled || isChecked
        )

        NormalBodyTextComponent(
            value = optionName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            textAlign = TextAlign.Start
        )

        if (isChecked && isQuantityEnabled) {
            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { newQuantity ->
                    viewModel.updateOptionQuantity(optionId, newQuantity)
                }
            )
        }

        NormalBodyTextComponent(
            value = "₹$price",
            modifier = Modifier
                .widthIn(min = 60.dp),
            textAlign = TextAlign.End
        )
    }
}