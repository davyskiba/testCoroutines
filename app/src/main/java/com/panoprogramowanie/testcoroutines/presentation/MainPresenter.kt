package com.panoprogramowanie.testcoroutines.presentation

import com.panoprogramowanie.testcoroutines.MainInteractor

class MainPresenter(
    private val mainInteractor: MainInteractor
) : BasePresenter<MainView>() {

    fun onButtonClicked() {
        view?.showConfirmationDialog()
    }

    suspend fun onConfirmed() {
        val title = mainInteractor.getTitle()
        view?.showTitle(title)
    }
}
