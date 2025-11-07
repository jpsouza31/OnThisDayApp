package com.app.onthisdayapp.presentation.data.dataSource

import com.app.onthisdayapp.presentation.data.external.APIService
import com.app.onthisdayapp.presentation.data.models.WikiApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataSource(
    private val apiService: APIService,
) {
    suspend fun getDayInfo(month: String, day: String): Response<WikiApiResponse> =
        withContext(Dispatchers.IO) {
            apiService.getDayInfo(month, day)
        }
}