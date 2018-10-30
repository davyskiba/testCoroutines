package com.panoprogramowanie.testcoroutines

import android.support.annotation.VisibleForTesting
import com.panoprogramowanie.testcoroutines.presentation.MainPresenter
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.android.Main

object DependencyInjector {
    fun getMainPresenter(
        mainInteractor: MainInteractor = getMainInteractor(),
        coroutineDispatcher: CoroutineDispatcher = getUIDispatcher()
    ) = MainPresenter(mainInteractor, coroutineDispatcher)

    fun getMainInteractor(mainRepository: MainRepository = getMainRepository()) = MainInteractor(mainRepository)
    fun getMainRepository(coroutineDispatcher: CoroutineDispatcher = getIODispatcher()) =
        MainRepository(coroutineDispatcher)

    fun getIODispatcher() = IODispatcher ?: Dispatchers.IO
    fun getUIDispatcher() = UIDispatcher ?: Dispatchers.Main

    private var IODispatcher: CoroutineDispatcher? = null
    private var UIDispatcher: CoroutineDispatcher? = null

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setIODispatcher(coroutineDispatcher: CoroutineDispatcher) {
        IODispatcher = coroutineDispatcher
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setUIDispatcher(coroutineDispatcher: CoroutineDispatcher) {
        UIDispatcher = coroutineDispatcher
    }
}
