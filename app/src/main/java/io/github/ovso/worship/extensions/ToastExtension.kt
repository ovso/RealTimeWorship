package io.github.ovso.worship.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.toast(any: Any?) {
    shortToast(this, any.toString())
}

fun Fragment.toast(any: Any?) {
    shortToast(context, any.toString())
}

private fun shortToast(context: Context?, any: Any?) {
    Toast.makeText(context, any.toString(), Toast.LENGTH_SHORT).show()
}
