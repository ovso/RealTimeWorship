package io.github.ovso.worship.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import timber.log.Timber

@BindingAdapter("load")
fun ImageView.load(url: String) {
    Timber.d("url = $url")
    Glide.with(this).load(url).into(this)
}
