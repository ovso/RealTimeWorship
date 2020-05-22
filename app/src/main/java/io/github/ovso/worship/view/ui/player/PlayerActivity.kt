package io.github.ovso.worship.view.ui.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.worship.R
import kotlinx.android.synthetic.main.player_activity.*

class PlayerActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.player_activity)
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }
}
