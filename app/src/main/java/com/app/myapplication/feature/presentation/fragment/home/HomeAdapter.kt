package com.app.myapplication.feature.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.databinding.ItemMovieBinding
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.util.Image.loadImage

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val items = arrayListOf<Movie>()

    lateinit var itemClickListener: (item: Movie) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.discoveryNormalTitle.text = movie.title
            binding.discoveryNormalImage.loadImage(movie.imgForeground)
            binding.root.setOnClickListener {
                itemClickListener.invoke(movie)
            }
        }
    }

    fun insertItems(newList: List<Movie>) {
        val oldRangePosition = items.size
        val newRangePosition = newList.size

        items.clear()
        items.addAll(newList)

        notifyItemRangeInserted(oldRangePosition, newRangePosition)
    }
}