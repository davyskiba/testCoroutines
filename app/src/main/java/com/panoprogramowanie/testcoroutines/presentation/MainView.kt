package com.panoprogramowanie.testcoroutines.presentation

interface MainView {
    fun showTitle(title: String)
    fun showConfirmationDialog(onConfirm: suspend () -> Unit)
}