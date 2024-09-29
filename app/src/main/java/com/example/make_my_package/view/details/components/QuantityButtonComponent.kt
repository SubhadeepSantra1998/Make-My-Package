package com.example.make_my_package.view.details.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.make_my_package.core.components.NormalTitleTextComponent

@Composable
fun QuantityButtonComponent(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
    ) {
        NormalTitleTextComponent(value = text)
    }
}