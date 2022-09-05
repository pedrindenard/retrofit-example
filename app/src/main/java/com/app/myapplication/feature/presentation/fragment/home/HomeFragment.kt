package com.app.myapplication.feature.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.databinding.FragmentMovieBinding
import com.app.myapplication.util.Animation
import com.app.myapplication.util.Animation.endAnimation
import com.app.myapplication.util.Animation.startAnimation
import com.app.myapplication.util.RecyclerView.getParcelable
import com.app.myapplication.util.RecyclerView.setAdapterGrid
import com.app.myapplication.util.RecyclerView.setParcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private val mainAdapter by lazy { HomeAdapter() }

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.homeRecyclerView.getParcelable(savedInstanceState, "instance")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        _binding?.homeRecyclerView?.setParcelable(outState, "instance")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getPopularMovies()
    }

    override fun onCreateView(inflater: LayoutInflater, view: ViewGroup?, bundle: Bundle?): View {
        _binding = FragmentMovieBinding.inflate(inflater, view, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
        setAdapter()
    }

    private fun setListeners() {
        mainAdapter.itemClickListener = { item ->
            findNavController().navigate(
                R.id.details_fragment,
                Bundle().apply { putSerializable("movie", item) },
                Animation.animFromRightToLeft
            )
        }

        binding.error.tryAgainButton.setOnClickListener {
            viewModel.getPopularMovies()
        }
    }

    private fun setAdapter() = binding.homeRecyclerView.setAdapterGrid(mainAdapter)

    private fun setObservers() {
        viewModel.uiResponseEvent.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    mainAdapter.insertItems(it.data.results)
                    binding.error.root.endAnimation()
                }
                is Resource.Failure -> {
                    binding.error.root.startAnimation()
                }
                is Resource.Error -> {
                    binding.error.root.startAnimation()
                }
                is Resource.InvalidToken -> {
                    binding.error.root.startAnimation()
                }
            }
        }

        viewModel.uiLoadingEvent.observe(viewLifecycleOwner) {
            binding.loading.root.isVisible = it
        }
    }
}