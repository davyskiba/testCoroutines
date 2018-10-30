package com.panoprogramowanie.testcoroutines.presentation

import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

abstract class BasePresenter<T>(private val baseCoroutineDispatcher: CoroutineDispatcher) : CoroutineScope {

    private var baseCoroutineScope: CoroutineScope? = null

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setTestCoroutineContext(coroutineScope: CoroutineScope?) {
        this.baseCoroutineScope = coroutineScope
    }

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = baseCoroutineScope?.coroutineContext ?: baseCoroutineDispatcher+job

    var view: T? = null

    private var isViewResumed: Boolean = false

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * onAttachedToWindow() method.
     */
    open fun attach(view: T) {
        this.view = view
        job = Job()
        isViewResumed = true
    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * onDetachedFromWindow() method.
     */
    open fun detach() {
        isViewResumed = false
        view = null
        job.cancel()
    }
}
