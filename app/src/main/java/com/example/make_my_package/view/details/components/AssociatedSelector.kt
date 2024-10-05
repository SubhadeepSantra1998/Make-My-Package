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
            val selectedCount = viewModel.selectedOptions.value
                .filter { it.value && specification.list.any { option -> option._id == it.key } }.size

            NormalTitleTextComponent(value = specification.name.firstOrNull() ?: "Specification")
            NormalBodyTextComponent(value = "Choose up to ${specification.max_range}")

            specification.list.forEach { option ->
                val isChecked = viewModel.selectedOptions.value[option._id] ?: false

                SpecificationCheckboxItem(
                    optionName = option.name.firstOrNull() ?: "",
                    price = option.price,
                    isChecked = isChecked,
                    isQuantityEnabled = specification.user_can_add_specification_quantity,
                    onCheckedChange = { isChecked ->
                        viewModel.onOptionSelected(
                            optionId = option._id,
                            isSelected = isChecked,
                            price = option.price,
                            maxRange = specification.max_range,
                            specificationId = specification._id
                        )
                    },
                    viewModel = viewModel,
                    optionId = option._id,
                    isOptionEnabled = true
                )
            }
        }
    }
}