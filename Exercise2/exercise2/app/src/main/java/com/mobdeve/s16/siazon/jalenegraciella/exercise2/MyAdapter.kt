package com.mobdeve.s16.siazon.jalenegraciella.exercise2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the RecyclerView, managing a list of PostKotlin items.
class MyAdapter(private val posts: ArrayList<PostKotlin>) : RecyclerView.Adapter<MyViewHolder>() {

    /**
     * Called when RecyclerView needs a new ViewHolder to represent an item.
     * Inflates the layout for a single post item and creates a new MyViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        // Inflate the item layout (post_layout.xml)
        val view = inflater.inflate(R.layout.post_layout, parent, false)

        // Create and return a new ViewHolder with the inflated view
        return MyViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method updates the contents of the ViewHolder with the data.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Get the data item at the current position
        val currentPost: PostKotlin = posts[position]

        // Bind the data to the ViewHolder
        holder.bindData(currentPost)
    }

    /**
     * Returns the total number of items the RecyclerView should display.
     */
    override fun getItemCount(): Int {
        return posts.size
    }
}
