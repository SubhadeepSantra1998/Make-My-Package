package com.example.make_my_package.view.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.make_my_package.core.components.NormalBodyTextComponent

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(64.dp) // Add corner radius here
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuantityButtonComponent(
            text = "-",
            onClick = {
                if (quantity > 1) onQuantityChange(quantity - 1)
            }
        )

        NormalBodyTextComponent(value = "$quantity")

        QuantityButtonComponent(
            text = "+",
            onClick = {
                onQuantityChange(quantity + 1)
            }
        )
    }
}