package com.app.myapplication.util

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object RecyclerView {

    fun RecyclerView.getParcelable(savedInstanceState: Bundle?, key: String) {
        layoutManager?.onRestoreInstanceState(savedInstanceState?.getParcelable(key))
    }

    fun RecyclerView.setParcelable(outState: Bundle?, key: String) {
        outState?.putParcelable(key, layoutManager?.onSaveInstanceState())
    }

    inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.setAdapterLinear(
        mainAdapter: RecyclerView.Adapter<T>
    ) {
        layoutManager = LinearLayoutManager(context)
        adapter = mainAdapter
        setHasFixedSize(true)
    }

    inline fun <reified T : RecyclerView.ViewHolder> RecyclerView.setAdapterGrid(
        mainAdapter: RecyclerView.Adapter<T>
    ) {
        layoutManager = GridLayoutManager(context, 3)
        adapter = mainAdapter
        setHasFixedSize(true)
    }
}