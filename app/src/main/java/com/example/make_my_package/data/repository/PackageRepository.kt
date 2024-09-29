package com.example.make_my_package.data.repository

import android.content.Context
import com.example.make_my_package.data.model.PackageModel
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class PackageRepository {

    fun getPackageModelFromJsonStream(inputStream: InputStream): PackageModel? {
        return try {
            val reader = InputStreamReader(inputStream)
            Gson().fromJson(reader, PackageModel::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}