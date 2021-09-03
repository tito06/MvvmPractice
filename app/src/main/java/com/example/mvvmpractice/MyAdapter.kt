package com.example.mvvmpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmpractice.databinding.ItemRvBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MovieViewHolder>(){

    var movieList = mutableListOf<Movie>()


    fun setMovies(movies: List<Movie>){
        this.movieList= movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]
        holder.binding.textView.text = movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
       return movieList.size
    }

    class MovieViewHolder(val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root){}
}