package com.panoprogramowanie.testcoroutines

class MainInteractor(private val mainRepository: MainRepository) {
    suspend fun getTitle() = mainRepository.getTitle()
}
