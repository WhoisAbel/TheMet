package io.github.metmuseum.themet.arts.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.metmuseum.R
import io.github.metmuseum.databinding.FragmentArtIdListBinding
import io.github.metmuseum.themet.common.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ArtIdListFragment : Fragment() {

    lateinit var navController: NavController

    private var binding by autoCleared<FragmentArtIdListBinding>()
    var adapter by autoCleared<ArtIdListAdapter>()
    private val viewModel: ArtIdListViewModel by viewModels()
    private var queryJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentArtIdListBinding.inflate(
            inflater,
            container,
            false
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
        navController = findNavController()
    }

    private fun initView() {

        binding.apply {

            if (viewModel.getKeyboardVisibility()) {
                etSearch.requestFocus()
                showKeyboard(etSearch)
            }

            etSearch.doAfterTextChanged {
                if (queryJob?.isActive == true)
                    queryJob?.cancel()

                queryJob = lifecycleScope.launchWithErrorHandler {
                    if (it.toString().trim().length > 2) {
                        delay(1200)
                        viewModel.getData(it.toString().trim())
                    }
                }
            }
        }

        initRecyclerView()
    }

    private fun initObservation() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is ArtIdListUiState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                uiState.exceptions.message,
                                Toast.LENGTH_LONG
                            ).show()
                            binding.pbLoadingList.visibility = View.GONE
                        }
                        ArtIdListUiState.Loading -> {
                            binding.pbLoadingList.visibility = View.VISIBLE
                        }
                        is ArtIdListUiState.Success -> {
                            binding.pbLoadingList.visibility = View.GONE
                            if (uiState.artIdList.total != 0) {
                                adapter.submitList(uiState.artIdList.objectIDs)
                                binding.rvArtIdList.smoothScrollToPosition(0)
                            } else
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.msg_empty_List),
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = ArtIdListAdapter { id ->
            viewModel.setLatestSearchKey(binding.etSearch.text.toString())
            viewModel.setKeyboardVisibility(false)

            safeNavigate(
                navController,
                ArtIdListFragmentDirections.actionArtIdListToArtDetails(id)
            )

        }

        binding.apply {
            rvArtIdList.setHasFixedSize(true)
            rvArtIdList.adapter = adapter
        }
    }

    override fun onPause() {
        hideKeyboard()
        super.onPause()
    }

}