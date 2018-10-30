package com.panoprogramowanie.testcoroutines.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.panoprogramowanie.testcoroutines.DependencyInjector
import com.panoprogramowanie.testcoroutines.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.coroutines.experimental.suspendCoroutine

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { DependencyInjector.getMainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)

        button.setOnClickListener { presenter.onButtonClicked() }
    }

    override suspend fun showConfirmationDialog(): Unit = suspendCoroutine {
        AlertDialog.Builder(this)
            .setTitle("Doing thingy")
            .setMessage("Are you sure?")
            .setPositiveButton("yes") { _, _ -> it.resume(Unit) }
            .setOnDismissListener { it.cancel() }
            .create()
            .show()
    }

    override fun showTitle(titleText: String) {
        hello.text = titleText
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
