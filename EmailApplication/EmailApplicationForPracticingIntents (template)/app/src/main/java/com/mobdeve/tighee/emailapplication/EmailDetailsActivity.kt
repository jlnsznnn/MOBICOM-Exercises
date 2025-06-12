package com.mobdeve.tighee.emailapplication

import android.content.Intent // Needed to use Intent for receiving/sending data
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.tighee.emailapplication.databinding.ActivityEmailDetailsBinding

// Displays an individual email’s details and lets the user delete it.
class EmailDetailsActivity : AppCompatActivity() {

    // Static keys for email intent data
    companion object {
        const val RECEIVER_KEY = "RECEIVER_KEY"
        const val SUBJECT_KEY = "SUBJECT_KEY"
        const val BODY_KEY = "BODY_KEY"
        const val POSITION_KEY = "POSITION_KEY"
    }

    // No class-level variables for views since we're using ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding for the EmailDetailsActivity
        val viewBinding : ActivityEmailDetailsBinding = ActivityEmailDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        /*  TODO:
         *      1. Get intent coming from the launcher via the Adapter
         *      2. Extract information (receiver, subject, body) + position from Intent
         *      3. Set TextViews with the appropriate data received
         *      NOTE: Make sure to store the position in case we need to send it back for deletion.
         *            For the sender, you can hard code it as "From: me"
         * */

        /**
         * Receiving Intent:
         * This activity was started from MainActivity via MyAdapter when a user clicked on an email.
         * That intent carried data (receiver, subject, body, and list position).
         * We're now extracting those pieces of data using intent.getXExtra().
         */

        // Get the email data from the intent
        val receiver = intent.getStringExtra(RECEIVER_KEY)
        val subject = intent.getStringExtra(SUBJECT_KEY)
        val body = intent.getStringExtra(BODY_KEY)
        val position = intent.getIntExtra(POSITION_KEY, -1)

        // Set TextViews
        viewBinding.emailReceiverTv.text = "To: $receiver"
        viewBinding.emailSubjectTv.text = subject
        viewBinding.emailBodyTv.text = body
        viewBinding.emailSenderTv.text = "From: me" // hardcoded

        /**
         * Sending Intent back:
         * If the user clicks the delete button, we want to delete this email from the main list.
         * So, we prepare an Intent that carries the position of the email to be deleted.
         * We send it back to MainActivity as a "result" using setResult().
         */

        // Set logic of delete button
        viewBinding.deleteIbtn.setOnClickListener(View.OnClickListener {
                /*  TODO:
                 *      1. Declare a new Intent
                 *      2. Place the position into the Intent
                 *      3. Set the result as OK passing the intent
                 *      4. Properly finish the activity
                 *      NOTE: We're passing back the position as we need to know what to delete in
                 *            our data / ArrayList
                 * */
            // New intent to carry the result data
            val resultIntent = Intent()
            //  Pass the email's position back
            resultIntent.putExtra(POSITION_KEY, position)
            // Send result back to the calling activity
            setResult(RESULT_OK, resultIntent)
            // Close this screen and return
            finish()
        })
    }
}