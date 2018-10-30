package com.panoprogramowanie.testcoroutines.presentation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.panoprogramowanie.testcoroutines.DependencyInjector
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private val view: MainView = mock()
    private lateinit var presenter: MainPresenter

    @BeforeEach
    fun setUp() {
        DependencyInjector.setIODispatcher(Dispatchers.Default)
        DependencyInjector.setUIDispatcher(Dispatchers.Default)
        presenter = DependencyInjector.getMainPresenter()
    }

    @Test
    fun onButtonsClicked() = runBlocking {
        presenter.attach(view)

        presenter.onButtonClicked()
        argumentCaptor<suspend () -> Unit> {
            verify(view).showConfirmationDialog(capture())

            firstValue.invoke()
        }

        verify(view).showTitle(any())
    }
}