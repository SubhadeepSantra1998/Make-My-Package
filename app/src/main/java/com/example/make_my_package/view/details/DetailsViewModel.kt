package com.example.make_my_package.view.details

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.make_my_package.data.model.PackageModel
import com.example.make_my_package.data.model.Specification
import com.example.make_my_package.data.repository.PackageRepository
import java.io.InputStream

class DetailsViewModel : ViewModel() {

    var packageModel = mutableStateOf<PackageModel?>(null)
        private set

    private val repository = PackageRepository()

    var selectedApartmentSizeId = mutableStateOf<String?>(null)
        private set
    var selectedOptions = mutableStateOf<Map<String, Boolean>>(emptyMap())
        private set
    var totalPrice = mutableIntStateOf(0)
        private set
    var quantity = mutableStateOf(1)
        private set
    var associateQuantity = mutableStateOf(1)
        private set
    var optionQuantities = mutableStateOf<Map<String, Int>>(emptyMap())
        private set


    fun loadPackageModelFromRaw(context: Context, rawResId: Int) {
        val inputStream: InputStream = context.resources.openRawResource(rawResId)
        val packageData = repository.getPackageModelFromJsonStream(inputStream)
        if (packageData != null) {
            packageModel.value = packageData
            calculateTotalPrice()
        }
    }

    fun getSpecificationByName(name: String): Specification? {
        return packageModel.value?.specifications?.find {
            it.name.contains(name)
        }
    }

    fun getAssociatedSpecifications(modifierId: String): List<Specification> {
        return packageModel.value?.specifications?.filter {
            it.modifierId == modifierId
        } ?: emptyList()
    }

    fun onApartmentSizeSelected(sizeId: String) {
        selectedApartmentSizeId.value = sizeId
        quantity.value = 1
        selectedOptions.value = emptyMap()
        optionQuantities.value = emptyMap()
        calculateTotalPrice()
    }

    fun onOptionSelected(
        optionId: String,
        isSelected: Boolean,
        price: Int,
        maxRange: Int,
        specificationId: String
    ) {
        val selectedOptionsForSpec = selectedOptions.value.filter {
            it.value && packageModel.value?.specifications?.any { spec ->
                spec._id == specificationId && spec.list.any { option -> option._id == it.key }
            } == true
        }

        if (isSelected && selectedOptionsForSpec.size >= maxRange) {
            val firstSelectedOptionId = selectedOptionsForSpec.keys.firstOrNull()
            firstSelectedOptionId?.let {
                val updatedOptions = selectedOptions.value.toMutableMap().apply {
                    put(it, false)
                }
                selectedOptions.value = updatedOptions
            }
        }

        val updatedOptions = selectedOptions.value.toMutableMap().apply {
            put(optionId, isSelected)
        }
        selectedOptions.value = updatedOptions

        if (!isSelected) {
            updateOptionQuantity(optionId, 1)
        }

        calculateTotalPrice()
    }

    fun updateQuantity(newQuantity: Int) {
        if (newQuantity > 0) {
            quantity.value = newQuantity
            calculateTotalPrice()
        }
    }

    fun updateOptionQuantity(optionId: String, newQuantity: Int) {
        if (newQuantity > 0) {
            val updatedQuantities = optionQuantities.value.toMutableMap().apply {
                put(optionId, newQuantity)
            }
            optionQuantities.value = updatedQuantities
            calculateTotalPrice()
        }
    }

    private fun calculateTotalPrice() {
        val basePrice = selectedApartmentSizeId.value?.let { sizeId ->
            packageModel.value?.specifications?.flatMap { it.list }
                ?.find { it._id == sizeId }?.price ?: 0
        } ?: 0

        val additionalPrice = selectedOptions.value.filter { it.value }
            .mapNotNull { (optionId, _) ->
                packageModel.value?.specifications?.flatMap { it.list }
                    ?.find { it._id == optionId }?.price?.let { price ->
                        price * (optionQuantities.value[optionId] ?: 1)
                    }
            }

        totalPrice.value = (basePrice + additionalPrice.sum()) * quantity.value
    }
}

