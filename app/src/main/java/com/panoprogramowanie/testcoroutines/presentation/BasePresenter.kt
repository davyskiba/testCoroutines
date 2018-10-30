package com.panoprogramowanie.testcoroutines.presentation

abstract class BasePresenter<T> {

    var view: T? = null

    private var isViewResumed: Boolean = false

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * onAttachedToWindow() method.
     */
    open fun attach(view: T) {
        this.view = view
        isViewResumed = true
    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * onDetachedFromWindow() method.
     */
    open fun detach() {
        isViewResumed = false
        view = null
    }
}
