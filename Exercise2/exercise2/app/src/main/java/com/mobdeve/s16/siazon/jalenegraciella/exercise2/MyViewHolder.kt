package com.mobdeve.s16.siazon.jalenegraciella.exercise2

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mobdeve.s16.siazon.jalenegraciella.exercise2.databinding.PostLayoutBinding
import androidx.recyclerview.widget.RecyclerView

// ViewHolder class to manage and bind the views of a single post item in the RecyclerView
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // ========== UI ELEMENT REFERENCES ==========

    // References to the views that display user info
    private val userImageIv: ImageView = itemView.findViewById(R.id.iconImg)          // User profile picture
    private val usernameTv: TextView = itemView.findViewById(R.id.usernameTv)         // Username (top section)
    private val locationTv: TextView = itemView.findViewById(R.id.locationTv)         // Optional location text

    // View for the image posted
    private val postImageIv: ImageView = itemView.findViewById(R.id.postImg)          // Main post image

    // Views for like button, caption, and post date
    private val likeButtonIb: ImageButton = itemView.findViewById(R.id.likeBtn)       // Like button
    private val captionUsernameTv: TextView = itemView.findViewById(R.id.usernameTv2) // Username in the caption section
    private val captionTextTv: TextView = itemView.findViewById(R.id.captionTv)       // Caption text
    private val datePostedTv: TextView = itemView.findViewById(R.id.dateTv)           // Date the post was made

    // Layout container for the caption block (may be hidden if no caption)
    private val captionContainer : LinearLayout = itemView.findViewById(R.id.captionLayout)

    // Holds the current data model for access during UI updates (like toggling like status)
    private lateinit var currentPost: PostKotlin

    /**
     * Binds a PostKotlin object to the ViewHolder's views.
     * @param model The data model representing one post.
     */
    fun bindData(model: PostKotlin){
        currentPost = model // Save the model for later reference (e.g., when liking)

        // ========== Set user info ==========
        userImageIv.setImageResource(model.userImageId) // Set user profile image
        usernameTv.text = model.username                 // Set username

        // Show or hide location based on whether it's null
        if (model.location != null) {
            locationTv.text = model.location
            locationTv.visibility = View.VISIBLE
        } else {
            locationTv.visibility = View.GONE
        }

        // ========== Set post image ==========
        postImageIv.setImageResource(model.imageId)

        // ========== Like button logic ==========
        updateLikeButtonUI(model.liked) // Set like icon depending on whether the post is liked

        // Toggle the like state when the like button is clicked
        likeButtonIb.setOnClickListener {
            currentPost.liked = !currentPost.liked               // Flip like state
            updateLikeButtonUI(currentPost.liked)                // Update UI accordingly
        }

        // ========== Set caption logic ==========
        if (model.caption != null) {
            captionUsernameTv.text = model.username              // Set caption's username
            captionTextTv.text = model.caption                   // Set caption text
            captionContainer.visibility = View.VISIBLE           // Show caption container
        } else {
            captionContainer.visibility = View.GONE              // Hide if there's no caption
        }

        // ========== Set post date ==========
        datePostedTv.text = model.datePosted
    }

    /**
     * Updates the like button image based on the like status.
     * @param isLiked Whether the post is liked or not.
     */
    private fun updateLikeButtonUI(isLiked: Boolean) {
        if (isLiked) {
            likeButtonIb.setImageResource(R.drawable.ic_like_filled) // Show filled heart
        } else {
            likeButtonIb.setImageResource(R.drawable.ic_like_outline) // Show outlined heart
        }
    }
}
