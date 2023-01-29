package io.github.metmuseum.themet.arts.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import io.github.metmuseum.databinding.ItemArtIdListBinding
import io.github.metmuseum.themet.common.adapter.DataBoundListAdapter

class ArtIdListAdapter(
    private val artIdClickCallback: ((Int) -> Unit)?
) : DataBoundListAdapter<Int, ItemArtIdListBinding>(

    diffCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ItemArtIdListBinding {
        val binding = ItemArtIdListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.artId?.let { artId ->
                artIdClickCallback?.invoke(artId)
            }
        }
        return binding
    }

    override fun bind(binding: ItemArtIdListBinding, item: Int, position: Int) {
        binding.artId = item
    }
}