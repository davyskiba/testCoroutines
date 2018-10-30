package com.panoprogramowanie.testcoroutines.presentation

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

abstract class BasePresenter<T>(private val baseCoroutineDispatcher: CoroutineDispatcher) : CoroutineScope {

    lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = baseCoroutineDispatcher + job

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
