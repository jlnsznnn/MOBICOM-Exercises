package com.example.addcourseapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast // Used for showing short messsages
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.addcourseapplication.databinding.ActivityMainBinding // For view binding

class MainActivity : AppCompatActivity() {
    // Declare the binding variable
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Modified for view binding
        val viewBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Handle window insets to avoid overlapping with system bars
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewBinding.floatingActionButton.setOnClickListener {
            Toast.makeText(this, "Adding S15...", Toast.LENGTH_SHORT).show()
        }

        viewBinding.floatingActionButton3.setOnClickListener {
            Toast.makeText(this, "Adding S16...", Toast.LENGTH_SHORT).show()
        }
    }
}