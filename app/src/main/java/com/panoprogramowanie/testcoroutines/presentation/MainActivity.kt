package com.panoprogramowanie.testcoroutines.presentation

import android.app.AlertDialog
import android.os.Bundle
import com.panoprogramowanie.testcoroutines.DependencyInjector
import com.panoprogramowanie.testcoroutines.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.launch

class MainActivity : CoroutineScopeActivity(), MainView {

    private val presenter by lazy { DependencyInjector.getMainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch { presenter.attach(this@MainActivity) }

        button.setOnClickListener { presenter.onButtonClicked() }
    }

    override fun showConfirmationDialog(onConfirm: suspend () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Doing thingy")
            .setMessage("Are you sure?")
            .setPositiveButton("yes") { _, _ ->
                launch { onConfirm.invoke() }
            }
            .create()
            .show()
    }


    override fun showTitle(titleText: String) {
        hello.text = titleText
    }

    override fun onDestroy() {
        super.onDestroy()
        launch { presenter.detach() }
    }
}
