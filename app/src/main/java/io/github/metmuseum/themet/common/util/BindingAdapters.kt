package io.github.metmuseum.themet.common.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import io.github.metmuseum.R

@BindingAdapter("imageUrl", "loading")
fun bindImageUrl(imageView: ImageView, url: String?, loading: ProgressBar) {

    imageView.load(url) {
        error(R.drawable.image_placeholder)
        memoryCachePolicy(CachePolicy.ENABLED)
        listener(onSuccess = { _, _ ->
            loading.visibility = View.GONE
        },
            onError = { _, _ ->
                loading.visibility = View.GONE
            }
        )
    }
}

@BindingAdapter("visibleUnless")
fun bindVisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("invisibleUnless")
fun bindInvisibleUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}