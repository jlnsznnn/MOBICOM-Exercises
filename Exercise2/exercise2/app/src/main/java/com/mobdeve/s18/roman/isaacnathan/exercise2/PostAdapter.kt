package com.mobdeve.s18.roman.isaacnathan.exercise2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class PostAdapter(private val posts: ArrayList<PostKotlin>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.ig_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost: PostKotlin = posts[position]
        holder.bind(currentPost)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}