package io.github.ovso.worship.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("divider")
fun RecyclerView.defaultDivider(orientation: Int = RecyclerView.VERTICAL) {
  addItemDecoration(DividerItemDecoration(context, orientation))
}
