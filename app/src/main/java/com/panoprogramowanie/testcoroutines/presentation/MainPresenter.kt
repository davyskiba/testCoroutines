package com.panoprogramowanie.testcoroutines.presentation

import com.panoprogramowanie.testcoroutines.MainInteractor
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.launch

class MainPresenter(
    private val mainInteractor: MainInteractor,
    baseCoroutineDispatcher: CoroutineDispatcher
) : BasePresenter<MainView>(baseCoroutineDispatcher) {

    fun onButtonClicked() = launch {
        view?.showConfirmationDialog()
        val title = mainInteractor.getTitle()
        view?.showTitle(title)
    }
}