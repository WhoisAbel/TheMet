package io.github.metmuseum.themet.arts.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.request.CachePolicy
import dagger.hilt.android.AndroidEntryPoint
import io.github.metmuseum.R
import io.github.metmuseum.databinding.ArtDetailsFragmentBinding
import io.github.metmuseum.themet.common.util.autoCleared
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtDetailsFragment : Fragment() {

    private var binding by autoCleared<ArtDetailsFragmentBinding>()
    var adapter by autoCleared<ArtAdditionalImagesAdapter>()
    private val viewModel: ArtDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ArtDetailsFragmentBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initValue()

        initView()

        initObservation()

    }

    private fun initValue() {

        ArtDetailsFragmentArgs.fromBundle(requireArguments()).let {
            viewModel.getData(it.artId)
        }
    }

    private fun initView() {
        initRecyclerView()
    }

    private fun initObservation() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is ArtDetailsListUiState.Error -> {
                            binding.pbLoadingDetails.visibility = View.GONE
                            Toast.makeText(
                                requireContext(), uiState.exceptions.message, Toast.LENGTH_LONG
                            ).show()
                        }
                        ArtDetailsListUiState.Loading -> {
                            binding.pbLoadingDetails.visibility = View.VISIBLE
                        }
                        is ArtDetailsListUiState.Success -> {
                            binding.apply {
                                pbLoadingDetails.visibility = View.GONE
                                isDataLoad = true
                                artDetails = uiState.artDetails
                            }
                            if (uiState.artDetails.additionalImages?.isEmpty() == false)
                                adapter.submitList(uiState.artDetails.additionalImages)
                            else binding.rvArtAdditionalImages.visibility = View.GONE
                        }
                    }

                }
            }

        }
    }

    private fun initRecyclerView() {

        adapter = ArtAdditionalImagesAdapter { url ->

            binding.ivPrimaryImage.load(url) {
                binding.pbLoading.visibility = View.VISIBLE
                error(R.drawable.ic_the_met)
                diskCachePolicy(CachePolicy.DISABLED)
                memoryCachePolicy(CachePolicy.DISABLED)
                listener(onSuccess = { request, result ->
                    binding.pbLoading.visibility = View.GONE
                }, onError = { request, result ->
                    binding.pbLoading.visibility = View.GONE
                })
            }
        }

        binding.apply {
            rvArtAdditionalImages.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvArtAdditionalImages.setHasFixedSize(true)
            rvArtAdditionalImages.adapter = adapter
        }

    }

}