package com.panoprogramowanie.testcoroutines.presentation

interface MainView {
    fun showTitle(titleText: String)
    suspend fun showConfirmationDialog()
}