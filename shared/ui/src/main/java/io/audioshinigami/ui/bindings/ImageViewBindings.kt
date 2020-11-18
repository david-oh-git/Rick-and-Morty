/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2020 $today.hour24:$today.minute   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.audioshinigami.ui.bindings

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import io.audioshinigami.ui.R
import java.util.Locale
import kotlin.random.Random

/**
 * Set image loaded from url.
 *
 * @param url Image url to download and set as drawable.
 * @param placeholderId Drawable resource identifier to set while downloading image.
 */
@BindingAdapter("url", "placeholder", requireAll = false)
fun setImage(imageView: AppCompatImageView, url: String?, @DrawableRes placeholderId: Int?) {

    with(imageView) {
        load(url) {
            crossfade(true)
            placeholder(
                placeholderId?.let {
                    ContextCompat.getDrawable(context, it)
                } ?: run {
                    val placeholdersColors = resources.getStringArray(R.array.placeholders)
                    val placeholderColor = placeholdersColors[
                        Random.nextInt(placeholdersColors.size)
                    ]
                    ColorDrawable(Color.parseColor(placeholderColor))
                }
            )
        }
    }
}

/**
 * Set image loaded from url.
 *
 * @param url Image url to download and set as drawable.
 * @param placeholderId Drawable resource identifier to set while downloading image.
 */
@BindingAdapter("url", "placeholder", requireAll = false)
fun setImage(imageView: ShapeableImageView, url: String?, @DrawableRes placeholderId: Int?) {

    with(imageView) {
        load(url) {
            crossfade(true)
            placeholder(
                placeholderId?.let {
                    ContextCompat.getDrawable(context, it)
                } ?: run {
                    val placeholdersColors = resources.getStringArray(R.array.placeholders)
                    val placeholderColor = placeholdersColors[
                        Random.nextInt(placeholdersColors.size)
                    ]
                    ColorDrawable(Color.parseColor(placeholderColor))
                }
            )
        }
    }
}

/**
 * Binder that sets icon based on status.
 *
 * @param status The character's status , Dead or Alive ?
 */
@BindingAdapter("status", requireAll = false)
fun setStatus(imageView: AppCompatImageView, status: String?) {
    with(imageView) {
        when (status?.toLowerCase(Locale.ROOT)) {
            "alive" -> setImageResource(R.drawable.ic_alive)
            "dead" -> setImageResource(R.drawable.ic_dead)
            else -> setImageResource(R.drawable.ic_unknown)
        }
    }
}
