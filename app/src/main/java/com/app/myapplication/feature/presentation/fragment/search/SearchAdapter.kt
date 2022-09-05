package com.app.myapplication.feature.presentation.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.databinding.ItemSearchBinding
import com.app.myapplication.feature.domain.model.Movie
import com.app.myapplication.util.Image.loadImage
import com.app.myapplication.util.StringFormat

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    val items = arrayListOf<Movie>()

    lateinit var itemClickListener: (item: Movie) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.searchTitle.text = movie.title
            binding.searchDate.text = StringFormat.formatToDate(movie.releaseDate)
            binding.searchOverview.text = movie.overview
            binding.searchImage.loadImage(movie.imgForeground)
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

    fun clearItems() {
        val oldRangePosition = items.size

        items.clear()

        notifyItemRangeRemoved(0, oldRangePosition)
    }
}