package com.example.make_my_package.view.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.make_my_package.core.components.NormalBodyTextComponent
import com.example.make_my_package.data.model.Details
import com.example.make_my_package.data.model.Specification


@Composable
fun ApartmentSizeItem(
    isSelected: Boolean,
    onSelected: () -> Unit,
    apartmentSize: Details,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = { onSelected() }
        )

        NormalBodyTextComponent(
            value = apartmentSize.name.firstOrNull() ?: "",
            modifier = Modifier
                .weight(1f)
        )

        NormalBodyTextComponent(
            value = "₹${apartmentSize.price}",
            modifier = Modifier
                .widthIn(min = 80.dp),
            textAlign = TextAlign.End
        )
    }
}