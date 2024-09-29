package com.example.make_my_package.view.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.make_my_package.core.components.NormalBodyTextComponent
import com.example.make_my_package.core.components.NormalTitleTextComponent
import com.example.make_my_package.data.model.Specification
import com.example.make_my_package.view.details.DetailsViewModel

@Composable
fun AssociatedSelector(
    specifications: List<Specification>,
    viewModel: DetailsViewModel
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        specifications.forEach { specification ->
            NormalTitleTextComponent(value = specification.name.firstOrNull() ?: "Specification")

            NormalBodyTextComponent(value = "Choose up to 1")

            specification.list.forEach { option ->
                SpecificationCheckboxItem(
                    optionName = option.name.firstOrNull() ?: "",
                    price = option.price,
                    isChecked = viewModel.selectedOptions.value[option._id] ?: false,
                    isQuantityEnabled = specification.user_can_add_specification_quantity,
                    onCheckedChange = { isChecked ->
                        viewModel.onOptionSelected(option._id, isChecked, option.price)
                    },
                    viewModel = viewModel,
                    optionId = option._id
                )
            }
        }
    }
}