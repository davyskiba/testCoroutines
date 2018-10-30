package com.panoprogramowanie.testcoroutines

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.delay

class MainRepository(private val baseCoroutineDispatcher: CoroutineDispatcher) {
    suspend fun getTitle(): String {
        delay(2000)
        return "Coroutines are awesome"
    }
}