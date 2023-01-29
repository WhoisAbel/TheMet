package io.github.metmuseum.themet.arts.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import io.github.metmuseum.databinding.ItemAdditionalImagesBinding
import io.github.metmuseum.themet.common.adapter.DataBoundListAdapter

class ArtAdditionalImagesAdapter(
    private val additionalImageClickCallback: ((String) -> Unit)?
) : DataBoundListAdapter<String, ItemAdditionalImagesBinding>(

    diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ItemAdditionalImagesBinding {
        val binding = ItemAdditionalImagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.additionalImage?.let { url ->
                additionalImageClickCallback?.invoke(url)
            }
        }
        return binding
    }

    override fun bind(binding: ItemAdditionalImagesBinding, item: String, position: Int) {
        binding.additionalImage = item
    }
}