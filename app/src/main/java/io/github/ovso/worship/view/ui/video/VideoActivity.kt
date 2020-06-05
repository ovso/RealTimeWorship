package io.github.ovso.worship.view.ui.video

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.worship.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity(R.layout.activity_video) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(toolbar_video)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = intent.getStringExtra("title")
    val channelId = intent.getStringExtra("channel_id")
    if (savedInstanceState == null) {
      channelId?.let {
        supportFragmentManager.beginTransaction()
          .replace(
            R.id.fl_video_container, VideoFragment.newInstance(channelId),
            VideoFragment::class.java.name
          ).commitAllowingStateLoss()
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}
