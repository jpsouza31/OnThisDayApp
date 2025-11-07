package com.app.onthisdayapp.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.onthisdayapp.presentation.data.dataSource.DataSource
import com.app.onthisdayapp.presentation.data.external.ServiceApi
import com.app.onthisdayapp.presentation.data.external.StatusService
import com.app.onthisdayapp.presentation.data.repository.Repository
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate

class HomeViewModel : ViewModel() {
    var dayInfo by mutableStateOf("")
        private set

    var apiCallState: StatusService<*> by mutableStateOf(StatusService.Idle)
        private set

    fun updateDayInfo(dayInfoUpdated: String) {
        dayInfo = dayInfoUpdated
    }

    fun reset() {
        apiCallState = StatusService.Idle
    }

    fun getDayInfo() {
        viewModelScope.launch {
            val repository = Repository(
                DataSource(ServiceApi.retrofitService), viewModelScope
            )
            apiCallState = StatusService.Loading
            try {
                val today = LocalDate.now()
                val month = today.monthValue.toString().padStart(2, '0')
                println("OPAAAA -> " + month)
                val day = today.dayOfMonth.toString().padStart(2, '0')
                println("OPAAAA 1 -> " + day)
                val resetPasswordResponse = repository.getDayInfo(month, day)
                if (resetPasswordResponse.isSuccessful) {
                    apiCallState = StatusService.Success("")
                    dayInfo = resetPasswordResponse.body()?.selected?.get(0)?.text.toString()
                } else {
                    apiCallState = StatusService.UnknownError
                }
            } catch (_: IOException) {
                apiCallState = StatusService.UnknownError
            }
        }
    }

}