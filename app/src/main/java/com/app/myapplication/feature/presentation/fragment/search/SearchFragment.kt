package com.app.myapplication.feature.presentation.fragment.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.myapplication.R
import com.app.myapplication.core.Resource
import com.app.myapplication.databinding.FragmentSearchBinding
import com.app.myapplication.util.Animation
import com.app.myapplication.util.RecyclerView.getParcelable
import com.app.myapplication.util.RecyclerView.setAdapterLinear
import com.app.myapplication.util.RecyclerView.setParcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: Fragment() {

    private val viewModel by viewModel<SearchViewModel>()
    private val mainAdapter by lazy { SearchAdapter() }

    private lateinit var textWatcher: TextWatcher

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(text: Editable?) {
                binding.searchClear.isVisible = text?.isNotEmpty() == true
                binding.searchDefault.root.isVisible = text?.isEmpty() == true

                if (text!!.isNotEmpty()) {
                    viewModel.getSearchMovie(text.toString())
                } else {
                    viewModel.job.cancel()
                    mainAdapter.clearItems()
                }
            }
        }
        binding.searchInputLayout.addTextChangedListener(textWatcher)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.searchRecyclerView.getParcelable(savedInstanceState, "instance")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        _binding?.searchRecyclerView?.setParcelable(outState, "instance")
    }

    override fun onCreateView(inflater: LayoutInflater, view: ViewGroup?, bundle: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, view, false)
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

        binding.searchDefault.root.isVisible = mainAdapter.items.isEmpty()

        binding.searchClear.setOnClickListener {
            binding.searchInputLayout.text?.clear()
        }

        binding.searchClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setAdapter() = binding.searchRecyclerView.setAdapterLinear(mainAdapter)

    private fun setObservers() {
        viewModel.uiResponseEvent.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                mainAdapter.clearItems()
                mainAdapter.insertItems(it.data.results)
            }

            val notEmptyInput = binding.searchInputLayout.text.toString().isNotEmpty()
            val emptyList = mainAdapter.items.isEmpty()

            binding.notFound.root.isVisible = notEmptyInput && emptyList
        }

        viewModel.uiLoadingEvent.observe(viewLifecycleOwner) {
            binding.loading.root.isVisible = it
        }
    }

    override fun onPause() {
        super.onPause()
        binding.searchInputLayout.removeTextChangedListener(textWatcher)
    }
}