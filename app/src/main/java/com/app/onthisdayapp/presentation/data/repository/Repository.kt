package com.app.onthisdayapp.presentation.data.repository

import com.app.onthisdayapp.presentation.data.dataSource.DataSource
import com.app.onthisdayapp.presentation.data.models.WikiApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import retrofit2.Response

class Repository (
    private val dataSource: DataSource,
    private val externalScope: CoroutineScope
) {

    suspend fun getDayInfo(month: String, day: String): Response<WikiApiResponse> {
        return externalScope.async {
            dataSource.getDayInfo(month, day).also { result ->
            }
        }.await()
    }
}