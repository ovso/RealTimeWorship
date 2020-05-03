package io.github.ovso.worship.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.worship.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
/*
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
*/
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        title = "산정현교회"
    }

}
