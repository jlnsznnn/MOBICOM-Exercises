package com.mobdeve.s18.roman.isaacnathan.exercise2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class
MainActivity : AppCompatActivity() {

    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var postsList: ArrayList<PostKotlin>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- RecyclerView Setup ---

        // 1. Get Data
        postsList = DataHelper.initializeData()

        // 2. Find RecyclerView
        postsRecyclerView = findViewById(R.id.recyclerView)

        // 3. Create Adapter Instance
        postAdapter = PostAdapter(postsList)

        // 4. Set Adapter
        postsRecyclerView.adapter = postAdapter

        // 5. Set LayoutManager
        postsRecyclerView.layoutManager = LinearLayoutManager(this)

        postsRecyclerView.setHasFixedSize(true)
    }
}