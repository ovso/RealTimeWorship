package io.github.ovso.worship.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(text: String) {
    shortToast(this, text)
}

fun Fragment.toast(text: String) {
    shortToast(context, text)
}

private fun shortToast(context: Context?, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
