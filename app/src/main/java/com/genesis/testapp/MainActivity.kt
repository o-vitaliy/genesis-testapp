package com.genesis.testapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.genesis.testapp.presentation.common.OnNewIntentListener
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { i ->
            nav_host_fragment.childFragmentManager.fragments
                .mapNotNull { it as? OnNewIntentListener }
                .forEach { it.onNewIntent(i) }
        }

    }
}
