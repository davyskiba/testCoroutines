package com.panoprogramowanie.testcoroutines.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.panoprogramowanie.testcoroutines.DependencyInjector
import com.panoprogramowanie.testcoroutines.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter by lazy { DependencyInjector.getMainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)

        button.setOnClickListener { presenter.onButtonClicked() }
    }

    override fun showTitle(titleText: String) {
        hello.text = titleText
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
