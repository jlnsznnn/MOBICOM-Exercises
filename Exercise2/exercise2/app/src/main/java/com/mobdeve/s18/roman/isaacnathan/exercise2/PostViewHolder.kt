package com.mobdeve.s18.roman.isaacnathan.exercise2 // Use your actual package name

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // User Info Section
    private val userImageIv: ImageView = itemView.findViewById(R.id.user_image_iv)
    private val usernameTv: TextView = itemView.findViewById(R.id.username_tv)
    private val locationTv: TextView = itemView.findViewById(R.id.location_tv)

    // Post Image
    private val postImageIv: ImageView = itemView.findViewById(R.id.post_image_iv)

    // Actions and Post Info Section
    private val likeButtonIb: ImageButton = itemView.findViewById(R.id.like_button_ib)
    private val captionContainerLl: LinearLayout = itemView.findViewById(R.id.caption_container_ll)
    private val captionUsernameTv: TextView = itemView.findViewById(R.id.caption_username_tv)
    private val captionTextTv: TextView = itemView.findViewById(R.id.caption_text_tv)
    private val datePostedTv: TextView = itemView.findViewById(R.id.date_posted_tv)

    private lateinit var currentPost: PostKotlin

    fun bind(post: PostKotlin) {
        currentPost = post

        // User Info
        userImageIv.setImageResource(post.userImageId)
        usernameTv.text = post.username

        // Location Logic
        if (post.location != null) {
            locationTv.text = post.location
            locationTv.visibility = View.VISIBLE
        } else {
            locationTv.visibility = View.GONE
        }

        // Post Image
        postImageIv.setImageResource(post.imageId)

        // Like Button Logic
        updateLikeButtonUI(post.liked)
        likeButtonIb.setOnClickListener {
            currentPost.liked = !currentPost.liked
            updateLikeButtonUI(currentPost.liked)
        }

        // Caption Logic
        if (post.caption != null) {
            captionUsernameTv.text = post.username
            captionTextTv.text = post.caption
            captionContainerLl.visibility = View.VISIBLE
        } else {
            captionContainerLl.visibility = View.GONE
        }
        datePostedTv.text = post.datePosted
    }

    private fun updateLikeButtonUI(isLiked: Boolean) {
        if (isLiked) {
            likeButtonIb.setImageResource(R.drawable.ic_like_filled)
        } else {
            likeButtonIb.setImageResource(R.drawable.ic_like_outline)
        }
    }
}