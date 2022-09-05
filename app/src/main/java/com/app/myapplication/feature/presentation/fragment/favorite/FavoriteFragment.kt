package com.app.myapplication.feature.presentation.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.databinding.FragmentMovieBinding
import com.app.myapplication.util.Animation
import com.app.myapplication.util.RecyclerView.getParcelable
import com.app.myapplication.util.RecyclerView.setAdapterLinear
import com.app.myapplication.util.RecyclerView.setParcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private val mainAdapter by lazy { FavoriteAdapter() }

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

        val itemTouch = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                viewModel.removeMovieFromFavorite(
                    mainAdapter.items[position].id,
                    position,
                    requireContext()
                )
            }
        }

        ItemTouchHelper(itemTouch).attachToRecyclerView(binding.homeRecyclerView)
    }

    private fun setAdapter() = binding.homeRecyclerView.setAdapterLinear(mainAdapter)

    private fun setObservers() {
        viewModel.uiResponseEvent.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                mainAdapter.insertItems(it.data)
            }

            binding.empty.root.isVisible = mainAdapter.items.isEmpty()
        }

        viewModel.uiFavoriteEvent.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    mainAdapter.items.removeAt(it.data.first)
                    mainAdapter.removeItem(it.data.first)

                    Toast.makeText(
                        context,
                        it.data.second.asString(requireContext()),
                        Toast.LENGTH_SHORT
                    ).show()

                    binding.empty.root.isVisible = mainAdapter.items.isEmpty()
                }
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        it.data?.second?.asString(requireContext()),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    //Do nothing
                }
            }
        }

        viewModel.uiLoadingEvent.observe(viewLifecycleOwner) {
            binding.loading.root.isVisible = it
        }
    }
}