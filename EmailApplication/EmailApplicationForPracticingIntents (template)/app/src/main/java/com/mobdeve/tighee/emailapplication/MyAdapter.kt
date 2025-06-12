package com.mobdeve.tighee.emailapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.tighee.emailapplication.databinding.ItemLayoutBinding

// Displays the list of emails in the RecyclerView and launches EmailDetailsActivity when clicked.
class MyAdapter(private val data: ArrayList<Email>, private val myActivityResultLauncher: ActivityResultLauncher<Intent>) : Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Initialize the ViewBinding of an item's layout
        val itemViewBinding: ItemLayoutBinding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        val myViewHolder = MyViewHolder(itemViewBinding)

        /**
         * Intent for Email Details:
         * When an email item is clicked, open EmailDetailsActivity.
         * We pass the email's data and its position so it can be shown, and possibly deleted.
         */

        // Provide logic for clicking on anywhere on the entire itemView of the ViewHolder
        myViewHolder.itemView.setOnClickListener {
            /*  TODO:
             *      1. Declare an intent heading to EmailDetailsActivity
             *      2. Place Email info into the intent, including the current position
             *      3. Using the appropriate ActivityResultLauncher, launch using your intent
             * */
            val position = myViewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val email = data[position]
                val intent = Intent(parent.context, EmailDetailsActivity::class.java)
                intent.putExtra(EmailDetailsActivity.RECEIVER_KEY, email.receiver)
                intent.putExtra(EmailDetailsActivity.SUBJECT_KEY, email.subject)
                intent.putExtra(EmailDetailsActivity.BODY_KEY, email.body)
                intent.putExtra(EmailDetailsActivity.POSITION_KEY, position)
                myActivityResultLauncher.launch(intent)
            }
        }
        return myViewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}