package com.panoprogramowanie.testcoroutines.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.panoprogramowanie.testcoroutines.DependencyInjector
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlin.coroutines.experimental.CoroutineContext

open class CoroutineScopeActivity : AppCompatActivity(), CoroutineScope {

    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = baseCoroutineScope + job


    private val baseCoroutineScope by lazy { DependencyInjector.getUIDispatcher() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
