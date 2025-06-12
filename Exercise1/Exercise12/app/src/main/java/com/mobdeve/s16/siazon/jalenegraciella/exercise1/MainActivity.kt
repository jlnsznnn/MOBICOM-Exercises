package com.mobdeve.s16.siazon.jalenegraciella.exercise1

import android.os.Bundle
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobdeve.s16.siazon.jalenegraciella.exercise1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // View binding varirable
    private var like = false // Track like button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate binding
        val viewBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle like button
        viewBinding.likeBtn.setOnClickListener{
            if(like){
                viewBinding.likeBtn.setImageResource(R.drawable.ic_like_outline)
            } else {
                viewBinding.likeBtn.setImageResource(R.drawable.ic_like_filled)
            }
            like = !like
        }

    }
}