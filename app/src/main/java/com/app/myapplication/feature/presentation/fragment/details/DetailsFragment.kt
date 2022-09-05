package com.app.myapplication.feature.presentation.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.myapplication.core.Resource
import com.app.myapplication.databinding.FragmentDetailsBinding
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.util.Animation.startAnimation
import com.app.myapplication.util.Image.loadImage
import com.app.myapplication.util.StringFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val viewModel by viewModel<DetailsViewModel>()
    private val movie by lazy { requireArguments().getSerializable("movie") as Movie }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetails(movie.id)
    }

    override fun onCreateView(inflater: LayoutInflater, view: ViewGroup?, bundle: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, view, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataFromBundle()
        setListeners()
        setObservers()
    }

    private fun setDataFromBundle() {
        binding.detailsImage.loadImage(movie.imgBackground)
        binding.detailsShowcaseImage.loadImage(movie.imgForeground)

        binding.detailsTitle.text = movie.title
        binding.detailsReleaseDate.text = StringFormat.formatToDate(movie.releaseDate)
        binding.detailsOverview.text = movie.overview(context)

        binding.detailsRateNumber.text = movie.averageVote.toString()
        binding.detailsVoteCount.text = movie.averageVoteCount(context)

        binding.detailsRateStar.rating = movie.averageVote / 2
    }

    private fun setListeners() {
        binding.detailsClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.detailsFavorite.setOnClickListener {
            viewModel.addToFavoriteList(movie, it.context)
        }
    }

    private fun setObservers() {
        viewModel.uiResponseEvent.observe(viewLifecycleOwner) {
            if (it is Resource.Success) {
                binding.detailsOriginalTitleText.text = it.data.originalTitle

                binding.detailsGenresText.text = it.data.genre.ifEmpty { "-" }
                binding.detailsCountryText.text = it.data.company.ifEmpty { "-" }
                binding.detailsProductionText.text = it.data.country.ifEmpty { "-" }

                binding.detailsRuntimeText.text = StringFormat.formatToRuntime(it.data.runtime)

                binding.detailsAbout.startAnimation()
            }
        }

        viewModel.uiFavoriteEvent.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(context, it.data.asString(requireContext()), Toast.LENGTH_SHORT)
                        .show()
                }
                is Resource.Error -> {
                    Toast.makeText(context, it.data?.asString(requireContext()), Toast.LENGTH_SHORT)
                        .show()
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