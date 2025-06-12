package com.mobdeve.s16.siazon.jalenegraciella.exercise2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

// Main entry point of the app that displays the feed using a RecyclerView
class MainActivity : AppCompatActivity() {

    // Declaration of UI and data components
    private lateinit var postsRecyclerView: RecyclerView       // The RecyclerView to display the posts
    private lateinit var postList: ArrayList<PostKotlin>       // List of posts to display
    private lateinit var recyclerView: RecyclerView            // Unused duplicate (can be removed)
    private lateinit var postAdapter: MyAdapter                // Adapter to bind data to the RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables drawing behind system bars (like the status bar) for a fullscreen experience
        enableEdgeToEdge()

        // Sets the layout for this activity (defined in activity_main.xml)
        setContentView(R.layout.activity_main)

        // Adjusts padding based on system bar insets (safe area handling)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ========== RecyclerView Setup ==========

        // 1. Load data to display
        postList = DataHelper.initializeData()  // Load dummy or real post data

        // 2. Find RecyclerView in the layout
        postsRecyclerView = findViewById(R.id.recyclerView)

        // 3. Create an adapter instance and pass in the data
        postAdapter = MyAdapter(postList)

        // 4. Set the adapter to the RecyclerView
        postsRecyclerView.adapter = postAdapter

        // 5. Set the layout manager (LinearLayoutManager = vertical list)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)

        // 6. Optimization: improves performance if size is fixed and doesn't change with content
        postsRecyclerView.setHasFixedSize(true)
    }
}
