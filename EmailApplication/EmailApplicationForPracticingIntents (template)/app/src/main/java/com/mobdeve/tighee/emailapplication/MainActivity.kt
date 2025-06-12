package com.mobdeve.tighee.emailapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.tighee.emailapplication.databinding.ActivityMainBinding

// Displays the email list and handles adding or deleting emails via other activities.
class MainActivity : AppCompatActivity() {

    // Our static storage for the emails
    companion object {
        private val data = ArrayList<Email>()
    }

    // RecyclerView components; We will need these late; hence, why we're saving them here
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    /**
     * Intent for Adding New Email:
     * This launcher starts NewEmailActivity and expects a result back (a new email).
     */

    // If success, then add email. Otherwise, don't do anything.
    private val newEmailResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        // Check to see if the result returned is appropriate (i.e. OK)
        if (result.resultCode == RESULT_OK) {
            // Get new email data from result Intent
            /* TODO:
             *      1. Extract new email information from intent coming from NewEmailActivity,
             *      2. Create a new Email,
             *      3. Add Email object to data,
             *      4. Notify adapter of change [explore notifyItemInserted()]
             *      NOTE: Make sure to place new email at top of list.
             * */
            val intent = result.data
            val receiver = intent?.getStringExtra(NewEmailActivity.NEW_RECEIVER_KEY)
            val subject = intent?.getStringExtra(NewEmailActivity.NEW_SUBJECT_KEY)
            val body = intent?.getStringExtra(NewEmailActivity.NEW_BODY_KEY)

            if (receiver != null && subject != null && body != null) {
                val newEmail = Email(receiver, subject, body)
                data.add(0, newEmail) // Add to top of list
                myAdapter.notifyItemInserted(0)  // Notify adapter to rebind list
                recyclerView.scrollToPosition(0)
            }
        }
    }

    /**
     * Intent for Deleting Email:
     * This launcher starts EmailDetailsActivity and expects a position to delete.
     */

    // If result OK (in terms of deleting), then add email at position. Otherwise, don't do anything.
    // This launcher is passed into myAdapter so we can launch from an itemView click
    private val emailDetailsResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        // Check to see if the result returned is appropriate (i.e. OK)
        if (result.resultCode == RESULT_OK) {
            /* TODO:
             *      1. Get position sent back from EmailDetailsActivity
             *      2. Delete data point from ArrayList
             *      3. Notify adapter of change [explore notifyItemRemoved()]
             * */
            val intent = result.data
            val position = intent?.getIntExtra(EmailDetailsActivity.POSITION_KEY, -1)
            if (position != null && position != -1) {
                data.removeAt(position)
                myAdapter.notifyItemRemoved(position)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding for the MainActivity
        val viewBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Intent to open NewEmailActivity to compose a new email
        // Logic for creating a new email button
        viewBinding.newBtn.setOnClickListener(View.OnClickListener {
                /*  TODO:
                 *      1. Declare an intent heading to NewEmailActivity
                 *      2. Using the appropriate ActivityResultLauncher, launch using your intent
                 * */
            val intent = Intent(this, NewEmailActivity::class.java)
            newEmailResultLauncher.launch(intent)
            /**
             * Purpose: Open the "Compose Email" screen.
             * From: MainActivity
             * To: NewEmailActivity
             * Data passed: None (we just want to create a new email).
             */
        })

        // RecyclerView setup; Note how MyAdapter has emailDetailsResultLauncher
        this.recyclerView = viewBinding.recyclerView
        this.myAdapter = MyAdapter(data, emailDetailsResultLauncher)
        this.recyclerView.adapter = myAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}