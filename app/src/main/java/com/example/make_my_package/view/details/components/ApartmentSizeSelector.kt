package com.example.make_my_package.view.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.make_my_package.core.components.NormalBodyTextComponent
import com.example.make_my_package.core.components.NormalTitleTextComponent
import com.example.make_my_package.view.details.DetailsViewModel

@Composable
fun ApartmentSizeSelector(
    viewModel: DetailsViewModel
) {
    val item = viewModel.getSpecificationByName("Apartment Size")

    item?.let {
        var selectedSize by remember {
            mutableStateOf(item.list.find { it.is_default_selected }?._id)
        }

        LaunchedEffect(key1 = selectedSize) {
            selectedSize?.let { defaultSizeId ->
                viewModel.onApartmentSizeSelected(defaultSizeId)
            }
        }

        Column(modifier = Modifier.padding(horizontal = 12.dp)) {

            NormalTitleTextComponent(value = item.name.firstOrNull() ?: "Apartment Size")

            NormalBodyTextComponent(value = "Choose one")

            item.list.forEach { apartmentSize ->
                ApartmentSizeItem(
                    isSelected = selectedSize == apartmentSize._id,
                    onSelected = {
                        selectedSize = apartmentSize._id
                        viewModel.onApartmentSizeSelected(selectedSize.orEmpty())
                    },
                    apartmentSize = apartmentSize
                )
            }

            selectedSize?.let { sizeId ->
                val associatedSpecs = viewModel.getAssociatedSpecifications(sizeId)
                AssociatedSelector(associatedSpecs, viewModel)
            }
        }
    }
}