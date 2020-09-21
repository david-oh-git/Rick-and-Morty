package io.audioshinigami.ui.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.audioshinigami.ui.recyclerview.RecyclerViewItemDecoration

/**
 * Add an [RecyclerViewItemDecoration] to this RecyclerView.
 *
 * @param spacingPx Spacing in pixels to set as a item margin.
 */
@BindingAdapter("itemDecorationSpacing")
fun RecyclerView.setItemDecorationSpacing(spacingPx: Float) {
    addItemDecoration(
        RecyclerViewItemDecoration(spacingPx.toInt())
    )
}